
from sklearn.neighbors import KNeighborsRegressor
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_absolute_error
from sklearn.preprocessing import PolynomialFeatures
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import MinMaxScaler
from sklearn.preprocessing import RobustScaler
import pandas as pd 
plt.rc("font", family="Malgun Gothic")
plt.rcParams["axes.unicode_minus"] = False

class Model_Util:
    def __init__(self, data, target, test_size, random_state=42):
        # 모델 학습 및 평가를 위한 클래스
        # data = 입력 데이터,
        # target = 타겟 레이블,
        # test_size: 테스트 데이터 비율,
        # random_state: 데이터 분할을 위한 랜덤 시드 (기본값 42)
        

        self.data = data
        self.target = target
        self.test_size = test_size
        self.random_state = random_state

        self.train_input = None
        self.test_input = None
        self.train_target = None
        self.test_target = None

        # 데이터 분할,
        self.split_data()

        self.model = None
        self.poly = None
        self.scaler = None

        # 제곱한 값 추가
        self.train_poly, self.test_poly = self.add_square()  # add_square 호출하여 데이터 준비

        
    def split_data(self):
        #데이터를 훈련/테스트로 분할
        self.train_input, self.test_input, self.train_target, self.test_target = train_test_split(self.data,
                                                                                                  self.target,
                                                                                                  test_size=self.test_size, 
                                                                                                  random_state=self.random_state,
                                                                                                  )
    # 모델 훈련시키기
    def train_lr(self):
        """ LinearRegression 모델 학습 """
        self.model = LinearRegression()
        self.model.fit(self.train_input, self.train_target)
    
    
    # 모델 평가하기
    def evaluate_model(self):
        """ 모델 평가 """
        if self.model is None:
            print("훈련할 모델이 없습니다.")
            return
    
        ### 훈련 및 테스트 데이터로 결정계수 확인
        train_score = self.model.score(self.train_input, self.train_target)
        test_score = self.model.score(self.test_input, self.test_target)
    
        ### 4. 과적합 여부 판단
        print(f"훈련: {train_score} \n테스트: {test_score} \n과적합여부: {train_score - test_score}")
    
        if train_score - test_score < 0.1:
            print("과적합이 발생하지 않았습니다.")
        else:
            print(f"과적합여부 결과 {round(train_score - test_score, 3)}로 과적합이 발생할 수도 있습니다")
    
    
    def train_polynomial(self, degree=2):
        """ 다항 회귀 모델 학습 """
        self.poly = PolynomialFeatures(degree=degree, include_bias=False)
        train_poly = self.poly.fit_transform(self.train_input)
        test_poly = self.poly.transform(self.test_input)
    
        # Polynomial Regression 학습
        self.model = LinearRegression()
        self.model.fit(train_poly, self.train_target)
    
        return train_poly, test_poly
    
    
    def evaluate_polynomial(self, train_poly, test_poly):
        """ 다항 회귀 모델 평가 """
        if self.model is None:
            print("훈련한 모델이 없습니다.")
            return
    
        ### 훈련 및 테스트 데이터로 결정계수 확인
        train_score = self.model.score(train_poly, self.train_target)
        test_score = self.model.score(test_poly, self.test_target)
    
        ### 4. 과적합 여부 판단
        print(f"훈련: {train_score} \n테스트: {test_score} \n과적합여부: {train_score - test_score}")
    
        if train_score - test_score < 0.1:
            print("과적합이 발생하지 않았습니다.")
        else:
            print(f"과적합여부 결과 {round(train_score - test_score, 3)}로 과적합이 발생할 수도 있습니다")
    
    
    def train_standard_scaler(self, degree=2):
        """ StandardScaler 적용 후 모델 학습 """
        self.poly = PolynomialFeatures(degree=degree, include_bias=False)
        train_poly = self.poly.fit_transform(self.train_input)
        test_poly = self.poly.transform(self.test_input)
    
        # 변환기 클래스 생성하기
        self.ss = StandardScaler()
    
        ### 스케일링하기 위한 패턴 찾기
        self.ss.fit(train_poly)
    
        ### 찾은 패턴으로 변환(스케일링)하기
        train_scaled = self.ss.transform(train_poly)
        test_scaled = self.ss.transform(test_poly)
    
        # Linear Regression 모델 학습
        self.model = LinearRegression()
        self.model.fit(train_scaled, self.train_target)
    
        return train_scaled, test_scaled
    
    
    def train_minmax_scaler(self, degree=2):
        """ MinMaxScaler 사용 후 모델 학습 """
        self.poly = PolynomialFeatures(degree=degree, include_bias=False)
        train_poly = self.poly.fit_transform(self.train_input)
        test_poly = self.poly.transform(self.test_input)
    
        # 변환기 클래스 생성하기
        self.mm = MinMaxScaler()
    
        ### 스케일링하기 위한 패턴 찾기
        self.mm.fit(train_poly)
    
        ### 찾은 패턴으로 변환(스케일링)하기
        train_scaled = self.mm.transform(train_poly)
        test_scaled = self.mm.transform(test_poly)
    
        # Linear Regression 모델 학습
        self.model = LinearRegression()
        self.model.fit(train_scaled, self.train_target)
    
        return train_scaled, test_scaled
    
    
    def train_robust_scaler(self, degree=2):
        """ RobustScaler 사용 후 모델 학습 """
        self.poly = PolynomialFeatures(degree=degree, include_bias=False)
        train_poly = self.poly.fit_transform(self.train_input)
        test_poly = self.poly.transform(self.test_input)
    
        # 변환기 클래스 생성하기
        self.rs = RobustScaler()
    
        ### 스케일링하기 위한 패턴 찾기
        self.rs.fit(train_poly)
    
        ### 찾은 패턴으로 변환(스케일링)하기
        train_scaled = self.rs.transform(train_poly)
        test_scaled = self.rs.transform(test_poly)
    
        # Linear Regression 모델 학습
        self.model = LinearRegression()
        self.model.fit(train_scaled, self.train_target)
    
        return train_scaled, test_scaled
    
    
    def predict_and_evaluate(self, test_scaled):
        """ 예측 및 MAE 평가 """
        test_pred = self.model.predict(test_scaled)
        print(f"예측결과: {test_pred}")
    
        mae = mean_absolute_error(self.test_target, test_pred)
        print(f"평균 절대 오차 (MAE): {mae}")
        return mae
    
    
    def evaluate(self, train_scaled, test_scaled):
        """ Standard, MinMax, Robust 사용 후 평가 """
        if self.model is None:
            print("훈련한 모델이 없습니다.")
            return
    
        train_score = self.model.score(train_scaled, self.train_target)
        test_score = self.model.score(test_scaled, self.test_target)
    
        print(f"훈련: {train_score} \n테스트: {test_score}")
        print(f"과적합여부: {train_score - test_score}")
    
        if abs(train_score - test_score) < 0.1:
            print("과적합이 발생하지 않았습니다.")
        else:
            print(f"과적합여부 결과 {round(train_score - test_score, 3)}로 과적합이 발생할 수도 있습니다")



    ### 선형회귀모델 함수
    # 1차원 to 2차원
    def change_to_2dim(self, train_input, test_input):
        self.train_input = train_input.reshape(-1, 1)
        self.test_input = test_input.reshape(-1, 1)
    
        return self.train_input, self.test_input


    def coef_intercept(self):
        a = self.model.coef_
        print(f"기울기 a = {a}")
        
        # - y절편
        b = self.model.intercept_
        print(f"y절편 b = {b}")

        return a, b

    # 예측 함수 추가
    def predict_value(self, value):
        pred = self.model.predict([[value]])  # 예측 값 계산
        return pred


    # 훈련 독립변수 및 종속변수 산점도 및 추세선 그리기
    def plot_trendline(self, pred=None):
        """ 훈련 데이터와 예측값, 추세선 그리기 """
        # 모델이 학습한 기울기와 절편을 사용
        a = self.model.coef_[0]  # 기울기
        b = self.model.intercept_  # 절편
        
        # 훈련 데이터 시각화 (훈련 데이터와 실제 값)
        plt.scatter(self.train_input, self.train_target, c="red", label="training data")
        
        # 예측값 시각화
        if pred is not None:
            plt.scatter(50, pred[0], c="green", label="prediction")

        # 추세선(예측 기준선) 그리기: y = ax + b
        x_values = np.array([min(self.train_input), max(self.train_input)])
        y_values = a * x_values + b  # y = ax + b (기울기와 절편을 사용하여 예측값 계산)
        plt.plot(x_values, y_values, color="blue", label="trendline", alpha=0.7)

        # 그래프 설정
        plt.title("훈련데이터, 예측값, 추세선 그리기")
        plt.xlabel("독립변수")
        plt.ylabel("종속변수")
        plt.legend()
        plt.grid(linestyle="--")
        plt.show()
    

    # 제곱한 값과 원래 값을 이용해서 2차원 데이터로 만들기
    def add_square(self):
        # 훈련데이터 제곱한 값 추가하기
        train_poly = np.column_stack((self.train_input**2, self.train_input))
        # 테스트데이터 제곱한 값 추가하기
        test_poly = np.column_stack((self.test_input**2, self.test_input))

        return train_poly, test_poly

    # 다항회귀 선형 모델 훈련시키기
    def train_mlr(self):
        """ 다항회귀 모델 학습 """
        self.model = LinearRegression()
        self.model.fit(self.train_poly, self.train_target)

    def mlr_evaluate_model(self):
        """ 다항회귀 모델 평가 """
        if self.model is None:
            print("훈련할 모델이 없습니다.")
            return
    
        # 훈련 및 테스트 데이터로 결정계수 확인
        train_score = self.model.score(self.train_poly, self.train_target)
        test_score = self.model.score(self.test_poly, self.test_target)
    
        # 과적합 여부 판단
        print(f"훈련: {train_score} \n테스트: {test_score} \n과적합여부: {train_score - test_score}")
    
        if train_score - test_score < 0.1:
            print("과적합이 발생하지 않았습니다.")
        else:
            print(f"과적합여부 결과 {round(train_score - test_score, 3)}로 과적합이 발생할 수도 있습니다")

    # 예측하기
    def mlr_predict_value(self, value):
        pred = self.model.predict([[value**2, value]])
        print(f"예측값{pred}")
        return pred

    ### 모델이 알아낸, 기울기와 절편 확인
    def mlr_coef_intercept(self):
        a = self.model.coef_[0]
        b = self.model.coef_[1]
        print(f"기울기 a = {a}")
        print(f"기울기 b = {b}")
        
        # - y절편
        c = self.model.intercept_
        print(f"y절편 b = {b}")

        return a, b, c



    # 훈련 독립변수 및 종속변수 산점도 및 추세선 그리기
    def mlr_plot_trendline(self, pred=None):
        """ 훈련 데이터와 예측값, 추세선 그리기 """
        # 모델이 학습한 기울기와 절편을 사용
        a = self.model.coef_[0]  # x^2의 계수
        b = self.model.coef_[1]  # x의 계수
        c = self.model.intercept_ # 절편
        
        ### 훈련 데이터 시각화
        plt.scatter(self.train_input, self.train_target, c="red", label="training")
        
        # 예측값 시각화 (예: 예측값이 있을 경우, 50에 대해 예측한 값을 시각화)
        if pred is not None:
            plt.scatter(50, pred[0], c="green", label="prediction")
    
        # 추세선(예측 기준선) 그리기: y = ax^2 + bx + c
        x_values = np.linspace(min(self.train_input), max(self.train_input), 100)
        y_values = a * x_values**2 + b * x_values + c 
        
        plt.plot(x_values, y_values, color="blue", label="trendline", alpha=0.7)
    
        # 그래프 설정
        plt.title("훈련데이터, 예측값, 추세선 그리기")
        plt.xlabel("독립변수")
        plt.ylabel("종속변수")
        plt.legend()
        plt.grid(linestyle="--")
        plt.show()
