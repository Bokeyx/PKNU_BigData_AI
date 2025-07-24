// React import 하기
import React from "react"

function Card(props) {
    return(
        <div style={{
            // div 테두리 선 그리기
            border: "1px solid gray",
            // 테두리 사가 라운드 주기
            borderRadius: "8px",
            // 안쪽 여백
            padding: "16px",
            // 외부 아래쪽 여백 주기
            marginBottom: "12px",
            // 배경색
            backgroundColor: "darkgray"
        }}>
            <h3>사용자 이름: {props.name}</h3>
            <p> * 직업: {props.job}</p>
        </div>
    );
}

export default Card;