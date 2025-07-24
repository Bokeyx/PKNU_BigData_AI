### 모델 불러들이는 라이브러리
import joblib
import numpy as np

### 저장되어 있는 모델 파일 읽어들이기
model = joblib.load("./models/model/rf_fish_model.pkl")

### 예측을 수행하기 위한 함수 정의
# - 웹 구조에서는 모든 처리는 함수로 정의하여 사용됨
# - features: React에서 사용자가 입력한 값(독립변수)
def getPredict(features) :
    ### 독리변수는 2차원로
    input_array = np.array([features])
    
    ### 예측하기
    # - 예측결과는 리스트(배열) 타입
    prediction = model.predict(input_array)
    
    ### 콘솔에도 출력하기
    print(">>>>>>>>>> Prediction = ", prediction[0])
    
    ### 예측 결과 반환하기
    return prediction[0]