### 라이브러리 정의
# - 데이터를 읽기 위한 라이브러리
import pandas as pd

# -  수치 계산 라이브러리
import numpy as np

# - 데이터 가공 라이브러리
from sklearn.model_selection import train_test_split

# 선형회귀모델 라이브러리
from sklearn.linear_model import LinearRegression

### 변환기 모델(클래스) 라이브러리 정의
from sklearn.preprocessing import PolynomialFeatures

# - MAE 라이브러리 정의
from sklearn.metrics import mean_absolute_error

# - 데이터 스케일링 라이브러리
from sklearn.preprocessing import StandardScaler

class Model_Utile :
    def __init__(self):
        pass
    
    # 데이터 읽고 numpy 형태의 배열로 변환
    def data_read(self, file_path) :
        try :
            self.df = pd.read_csv(file_path)
            self.df.info()
            self.df.head(1)
            read_data = self.df.to_numpy()
            return read_data
        except :
            print("파일 형식이 올바르지 않습니다.")
            return ""

    ### 데이터 분류 하는 함수
    def data_split(self, data_full, data_input, test_size) :
        train_input, test_input, train_target, test_target = train_test_split(
            data_full, data_input, test_size=test_size, random_state=42)
        return train_input, test_input, train_target, test_target

    ### 훈련모델 생성 및 훈련
    def new_model(self, train_input, train_target, test_input, test_target) :
        lr = LinearRegression()
        lr.fit(train_input, train_target)
        train_score = lr.score(train_input, train_target)
        test_score = lr.score(test_input, test_target)
        if train_score < 1 and test_score < 1 and train_score - test_score < 0.09 and  train_score - test_score > 0.01 :
            print(f"훈련 결정계수 : {train_score}, 테스트 결정계수 : {test_score}, 과적합여부 : {train_score - test_score}")
            print("해당 모델은 사용 가능한 모델입니다.")
            print(" ")
        else :
            print("해당 모델은 사용할 수 없는 모델입니다.")
            print(" ")
    
    ### 특성 추가 함수
    def poly_model(self, train_input, train_target, test_input, test_target) :
        for idx in range(2, 5) :
            # 특성 차원 생성
            poly = PolynomialFeatures(degree=idx, include_bias=False)
            poly.fit(train_input)
            train_poly = poly.transform(train_input)
            test_poly = poly.transform(test_input)

            # 훈련 모델 생성 및 결과 확인
            print(" ")
            print(f"{idx} 차원 모델이 생성 되었습니다")
            self.new_model(train_poly, train_target, test_poly, test_target)
    
    ### 데이터 스케일링 추가 함수
    def scaled_model(self, train_input, train_target, test_input, test_target) :
        ss = StandardScaler()
        ss.fit(train_input)
        train_scaled = ss.transform(train_input)
        test_scaled = ss.transform(test_input)

        # 훈련 모델 생성 및 결과 확인
        self.new_model(train_scaled, train_target, test_scaled, test_target)

    ### 데이터 특성 + 스케일 함수
    def total_model(self, train_input, train_target, test_input, test_target) :
        for idx in range(2, 5) :
            # 특성 차원 생성
            poly = PolynomialFeatures(degree=idx, include_bias=False)
            poly.fit(train_input)
            train_poly = poly.transform(train_input)
            test_poly = poly.transform(test_input)

            # 데이터 스케일링
            ss = StandardScaler()
            ss.fit(train_poly)
            train_scaled = ss.transform(train_poly)
            test_scaled = ss.transform(test_poly)

            # 모델 생성 확인
            print(" ")
            print(f"{idx} 차원 모델이 생성 되었습니다")
            self.new_model(train_scaled, train_target, test_scaled, test_target)
