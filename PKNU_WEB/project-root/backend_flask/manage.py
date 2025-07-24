### Flask 최초 시작 파일로 사용
# - 파일명은 자유롭게: 보통 app.py or manage.py를 주로 사용함

### 플라스크 라이브러리 불러들이기
from flask import Flask, request, jsonify

### 예측함수 불러들이기
from views.pred.predict import getPredict

### CORS 허용을 위한 라이브러리 불러들이기
# - 이기종간 웹환경 간에 접속을 허용하기 위한 기능
from flask_cors import CORS

### 플라스크 서버 객체 생성하기
# - Flask(__name__)이 있는 파일이
#  -> 플라스크 서버의 최초 시작 파일이 됩니다.
app = Flask(__name__)

### 모든 도메인에 대해 CORS허용
# - React 서버에서 요청 및 응답이 가능하도록 처리
CORS(app)

### 테스트를 위해 함수 정의하기
# - SPringBoot에서 Controller의 URL 매핑방식과 유사한 구조로 되어있음
@app.route("/")
def index() :
    return "Index 페이지 입니다.(Test)"

### 생선종류 예측하는 함수 정의하기
@app.route("/predict", methods=["POST"])
def predict() :
    ### 파라미터 추출하기 React에서 Json형식으로 전달받음
    # get_json(): 전달받은 모든 파라미터를 json형식으로 받기
    data = request.get_json()
    
    ### 추출한 데이터의 타입 확인하기
    if isinstance(data, list) :
        features = data
    
    # - 전달 받은 형식이 딕셔너리({}) 형식 이면서 data 변수 내에 "features" key가 있는 경우 처리
    elif isinstance(data, dict) and "features" in data :
        # data는 딕셔너리 타입
        #  - 전달받은 데이터가 딕셔너리 이므로,
        #  - 데이터를 추출할 때는 key를 이용하여 추출
        #  - 전송받은 key는 features로 고정된 key값임
        #   -> value값은 리스트 타입으로 되어있음
        features = data["features"]
    
    # - 리스트 또는 딕셔너리 타입이 아닌경우 처리 
    #  -> 더 이상 처리 없이 React로 응답하기(json형식으로 응답)
    #  -> 끝에 400은 오류번호를 포함해서 반환(400 Error)
    else :
        return jsonify({"error" : "잘못된 입력 형식입니다."}), 400
    
    ### 예측을 수행하는 함수 호출
    result = getPredict(features=features)
    
    ### 결과값을 React로 반환하기
    # - 반환 형식은 json 형식으로 반환함(Restful 방식)
    # - React에서는 "prediction" 이름으로 예측결과값 추출하여
    #  -> 화면에 출력하게 됨..
    return jsonify({"prediction": result})
    # return "predict()함수 호출..."
    



### .py 파일의 최초 시작점(진입점)
if __name__ == "__main__":
    ### app.run() : 플라스크 서버 구동 처리
    # - debug=True : 코드 수정시 웹브라우저에 새로고침
    # - 서버 실행 명령: 프롬프트에서 -> python manage.py로 실행함
    # - 서버 대표 주소: http://localhost:5000 (http://127.0.0.1:5000)
    app.run(debug=True)