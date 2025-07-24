### Machine Running library install
from sklearn.neighbors import KNeighborsClassifier
from sklearn.linear_model import LogisticRegression

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

# - 결정트리 모델(클래스) 라이브러리 정의
from sklearn.tree import DecisionTreeClassifier
from sklearn.tree import plot_tree

### 교차검증 및 폴드 사용을 위한 라이브러리 정의
from sklearn.model_selection import cross_validate
from sklearn.model_selection import StratifiedKFold

import pandas as pd 

import seaborn as sns

### 라이브러리 정의
from scipy.stats import spearmanr

### 라이브러리 정의
from scipy.stats import pearsonr

### 하이퍼파라미터 튜닝 모델(클래스) 정의하기
from sklearn.model_selection import GridSearchCV

plt.rc("font", family="Malgun Gothic")

plt.rcParams["axes.unicode_minus"] = False

### 경고 메시지 없애기
# - 사이킷런 버전에 따라 오류가 아니니 안내(경고)메시지가 자주 나타남
# - 안내(경고) 메시지 없이 실행할 수 있도록 처리
from sklearn import set_config
set_config(display="text")

# ---------------------------------------------------------------------------------------

class classification_analysis:
    def __init__(self, data, target_column, test_size=0.2, random_state=42, poly_degree=2, scaler_type='standard', models=None, stratify=None):
        X = data.drop(columns=[target_column]).to_numpy()
        y = data[target_column].to_numpy()

        # 데이터셋 분리 (훈련셋과 테스트셋)
        self.train_input, self.test_input, self.train_target, self.test_target = train_test_split(
            X, y, test_size=test_size, random_state=random_state, stratify=stratify
        )

        # 다항 특성 생성
        self.poly = PolynomialFeatures(degree=poly_degree, include_bias=False)
        self.train_input_poly = self.poly.fit_transform(self.train_input)
        self.test_input_poly = self.poly.transform(self.test_input)

        # 스케일러 선택
        if scaler_type == "standard":
            self.scaler = StandardScaler()
        elif scaler_type == "minmax":
            self.scaler = MinMaxScaler()
        elif scaler_type == "robust":
            self.scaler = RobustScaler()
        else:
            raise ValueError("지원되지 않는 스케일러 유형입니다.")

        # 표준화 또는 다른 스케일링
        self.train_input_scaled = self.scaler.fit_transform(self.train_input)
        self.test_input_scaled = self.scaler.transform(self.test_input)

        # 사용할 모델 설정 (디폴트는 KNN, Logistic Regression, Decision Tree)
        if models is None:
            self.models = [
                KNeighborsClassifier(),
                LogisticRegression(random_state=random_state, max_iter=1000),
                DecisionTreeClassifier(max_depth=12, min_samples_split=10, min_samples_leaf=5, random_state=random_state)
            ]
        else:
            self.models = models

    def train_and_evaluate(self):
        for model in self.models:
            print(f"모델: {model.__class__.__name__}")

            # GridSearchCV를 사용한 하이퍼파라미터 튜닝
            if isinstance(model, KNeighborsClassifier):
                param_grid = {
                    'n_neighbors': [3, 5, 7, 9],
                }
            elif isinstance(model, LogisticRegression):
                param_grid = {
                    'C': [0.01, 0.1, 1, 10],
                }
            elif isinstance(model, DecisionTreeClassifier):
                param_grid = {
                    'max_depth': [5, 10, 15, None],
                    'min_samples_split': [2, 5, 10],
                    'min_samples_leaf': [1, 2, 4]
                }
            else:
                param_grid = {}

            # GridSearchCV 적용
            grid_search = GridSearchCV(model, param_grid, cv=5, n_jobs=-1, verbose=1)
            grid_search.fit(self.train_input_scaled, self.train_target)

            # 최적 파라미터 출력
            print(f"최적 파라미터: {grid_search.best_params_}")

            # 최적 모델로 학습 및 평가
            best_model = grid_search.best_estimator_
            train_score = best_model.score(self.train_input_scaled, self.train_target)
            test_score = best_model.score(self.test_input_scaled, self.test_target)
            print(f"훈련정확도: {train_score}, 테스트정확도: {test_score}, 과적합여부: {train_score - test_score}")
            
            if train_score - test_score > 0.1:
                print("과대적합으로 판단됩니다.")
            elif train_score - test_score <= 0:
                print("과소적합이 발생하였습니다.")
            else:
                print("사용가능한 과적합 상태입니다.")
            
            # 테스트 데이터로 예측하기
            test_pred = best_model.predict(self.test_input_scaled)
            
            # test_pred와 test_target 비교하기
            correct_prediction = test_pred == self.test_target  
            if correct_prediction.all():
                print("모든 예측이 정확합니다.")
            else:
                correct_indices = [i for i in range(len(test_pred)) if not correct_prediction[i]]
                print(f"예측에 실패한 인덱스: {correct_indices}")
                
            # 예측 결과 출력
            print(f"예측결과: {test_pred}")
            print(f"실제정답: {self.test_target}")
            
            # 성능 평가 지표들
            acc = accuracy_score(self.test_target, test_pred)
            pre = precision_score(self.test_target, test_pred, average='binary')
            rec = recall_score(self.test_target, test_pred, average='binary')
            f1 = f1_score(self.test_target, test_pred, average='binary')

            print(f"정확도:{acc:.4f}, 정밀도:{pre:.4f}, 재현율:{rec:.4f}, f1-score:{f1:.4f}")
            
            # 혼동 행렬 출력
            cm = confusion_matrix(self.test_target, test_pred)
            dist = ConfusionMatrixDisplay(confusion_matrix=cm)
            dist.plot()
            plt.show()
            print("")


    def get_data(self):
        return (self.train_input_scaled, self.test_input_scaled, self.train_target, self.test_target)
    
    def get_poly_data(self):
        return (self.train_input_poly, self.test_input_poly)
    
    def get_poly_feature_names(self):
        return self.poly.get_feature_names_out()
