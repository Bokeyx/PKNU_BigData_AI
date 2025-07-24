### 앙상블 모델
from sklearn.ensemble import RandomForestClassifier
from sklearn.ensemble import ExtraTreesClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.ensemble import HistGradientBoostingClassifier
from xgboost import XGBClassifier

### visualization library
import matplotlib.pyplot as plt

# Definition of the NumPy library
import numpy as np

### Definition of Library (Preprocessing Library)
from sklearn.model_selection import train_test_split

### 오차행렬 및 오차행렬도 라이브러리 정의하기
from sklearn.metrics import confusion_matrix
from sklearn.metrics import ConfusionMatrixDisplay

# - 변환기 모델(클래스) 라이브러리 정의하기
from sklearn.preprocessing import PolynomialFeatures
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import MinMaxScaler
from sklearn.preprocessing import RobustScaler

# 정확도
from sklearn.metrics import accuracy_score
# 정밀도
from sklearn.metrics import precision_score
# 재현율
from sklearn.metrics import recall_score
# f1-score
from sklearn.metrics import f1_score
# (**(rf_params if rf_params else {})),

# - 결정트리 모델(클래스) 라이브러리 정의
from sklearn.tree import DecisionTreeClassifier
from sklearn.tree import plot_tree

import pandas as pd 

import seaborn as sns


### 라이브러리 정의
from scipy.stats import spearmanr

### 라이브러리 정의
from scipy.stats import pearsonr

### 라이브러리 정의
from scipy.stats import randint, uniform


### 하이퍼파라미터 튜닝 모델(클래스) 정의하기
from sklearn.model_selection import GridSearchCV
from sklearn.model_selection import RandomizedSearchCV

plt.rc("font", family="Malgun Gothic")

plt.rcParams["axes.unicode_minus"] = False

### 경고 메시지 없애기
# - 사이킷런 버전에 따라 오류가 아니니 안내(경고)메시지가 자주 나타남
# - 안내(경고) 메시지 없이 실행할 수 있도록 처리
from sklearn import set_config
set_config(display="text")

class classification_analysis:
    def __init__(self, data, target_column, test_size=0.2, random_state=42, poly_degree=1, scalers=None, models=None, stratify=None):
        # 종속변수(target)와 독립변수(feature) 분리
        X = data.drop(columns=[target_column])
        y = data[target_column]

        # 훈련셋, 검증셋, 테스트셋 나누기
        self.train_input, self.test_input, self.train_target, self.test_target = train_test_split(
            X, y, test_size=test_size, random_state=random_state, stratify=stratify
        )
        
        # 훈련셋을 6:2:2 비율로 나누기 위해서 추가로 train_input과 val_input으로 나눔
        self.train_input, self.val_input, self.train_target, self.val_target = train_test_split(
            self.train_input, self.train_target, test_size=0.25, random_state=random_state, stratify=self.train_target
        )
        
        # 다항 특성 생성
        self.poly = PolynomialFeatures(degree=poly_degree, include_bias=False)
        self.train_input_poly = self.poly.fit_transform(self.train_input)
        self.test_input_poly = self.poly.transform(self.test_input)
        
        # 기본적으로 사용할 스케일러 리스트 지정
        if scalers is None:
            scalers = [StandardScaler(), MinMaxScaler(), RobustScaler()]
        self.scalers = scalers

        # 기본 스케일링 적용 (StandardScaler 사용)
        self.train_scaled = self.scalers[0].fit_transform(self.train_input)
        self.test_scaled = self.scalers[0].transform(self.test_input)

        # 사용할 모델들 설정
        if models is None:
            self.models = self.create_classifiers()
        else:
            self.models = models
        
    def create_classifiers(self):
        # 여러 가지 모델들을 초기화
        models = [
            RandomForestClassifier(),
            ExtraTreesClassifier(),
            GradientBoostingClassifier(),
            HistGradientBoostingClassifier(),
            XGBClassifier()
        ]
        return models
    
    def tune_hyperparameters(self, model, param_grid):
        # 그리드 서치를 통한 하이퍼파라미터 튜닝
        grid_search = GridSearchCV(model, param_grid, cv=5, n_jobs=-1, verbose=1)
        grid_search.fit(self.train_scaled, self.train_target)
        return grid_search.best_estimator_, grid_search.best_params_

    def train_and_evaluate(self, param_grids=None):
        # 모델 학습 및 평가
        for i, model in enumerate(self.models):
            print(f"\n모델: {model.__class__.__name__}")

            # 하이퍼파라미터 튜닝
            if param_grids and model.__class__.__name__ in param_grids:
                best_model, best_params = self.tune_hyperparameters(model, param_grids[model.__class__.__name__])
                print(f"최적 파라미터: {best_params}")
            else:
                # 파라미터 튜닝 없이 기본 모델로 학습
                best_model = model

            # 스케일러 적용
            for scaler in self.scalers:
                print(f"--- {scaler.__class__.__name__} 적용 ---")
                train_scaled = scaler.fit_transform(self.train_input)
                test_scaled = scaler.transform(self.test_input)
                
                # 모델 학습
                best_model.fit(train_scaled, self.train_target)
                
                # 훈련과 테스트 정확도 계산
                train_score = best_model.score(train_scaled, self.train_target)
                test_score = best_model.score(test_scaled, self.test_target)
                print(f"훈련정확도: {train_score}, 테스트정확도: {test_score}, 과적합여부: {train_score - test_score}")

                # 과적합 또는 과소적합 판별
                if train_score - test_score > 0.1:
                    print("과대적합으로 판단됩니다.")
                elif train_score - test_score < 0:
                    print("과소적합이 발생하였습니다.")
                else:
                    print("사용가능한 과적합 상태입니다.")

                # 예측 결과 평가
                self.evaluate_metrics(best_model, test_scaled, self.test_target)

            # 다항 특성 적용
            print("\n--- Polynomial Features ---")
            best_model.fit(self.train_input_poly, self.train_target)
            train_score = best_model.score(self.train_input_poly, self.train_target)
            test_score = best_model.score(self.test_input_poly, self.test_target)
            print(f"훈련정확도: {train_score}, 테스트정확도: {test_score}, 과적합여부: {train_score - test_score}")
            # 과적합 또는 과소적합 판별
            if train_score - test_score > 0.1:
                print("과대적합으로 판단됩니다.")
            elif train_score - test_score < 0:
                print("과소적합이 발생하였습니다.")
            else:
                print("사용가능한 과적합 상태입니다.")
                
            self.evaluate_metrics(best_model, self.test_input_poly, self.test_target)

    def evaluate_metrics(self, model, test_data, test_target):
        # 예측 결과 평가
        test_pred = model.predict(test_data)
        acc = accuracy_score(test_target, test_pred)
        pre = precision_score(test_target, test_pred, average='macro')
        rec = recall_score(test_target, test_pred, average='macro')
        f1 = f1_score(test_target, test_pred, average='macro')

        print(f"정확도:{acc:.4f}, 정밀도:{pre:.4f}, 재현율:{rec:.4f}, f1-score:{f1:.4f}")

        # 혼동행렬 출력
        self.confusion_matrix(test_pred)

    def confusion_matrix(self, test_pred):
        cm = confusion_matrix(self.test_target, test_pred)
        dist = ConfusionMatrixDisplay(confusion_matrix=cm)
        dist.plot()
        plt.show()
        print("")

    def get_data(self):
        return (self.train_scaled, self.test_scaled, self.train_target, self.test_target)
    
    def get_poly_data(self):
        return (self.train_input_poly, self.test_input_poly)
    
    def get_poly_feature_names(self):
        return self.poly.get_feature_names_out()

