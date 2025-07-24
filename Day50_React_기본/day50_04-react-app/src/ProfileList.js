// React import 하기
import React from "react"

// 하위 컴포넌트 import 하기: Card.js
import Card from "./Card"

/*
 * < 사용자 목록 데이터 정의하기>
 * - 배열 타입으로 정의
 * - 배열 내 데이터는 json(딕셔너리, 오브젝트) 타입으로 정의
 * - json 내에서 사용하는 key는 변수처럼 사용됨(문자열 아님)
*/
const users =  [
    {id: 1, name: "홍길동", job: "프론트엔드 개발자"},    
    {id: 2, name: "이순신", job: "백엔드 개발자"},    
    {id: 3, name: "김유신", job: "웹 디자이너"}
];

function ProfileList(){
    return (
        <div>
            <h2>사용자 목록</h2>
            <hr/>

            {/* user의 리스트를 반복하기
                - JSX 문법 적용: 리스트를 반복하면서 내부 json데이터를 map 라이브러리로 받음
                - 하나의 컴포넌트에 반복하여 값을 전달 할 경우
                 -> key 속성을 필수로 작성해야함 (key로 사용할 값을 지정, id 사용)
                - 사용문법: users.map((user) => (<Card key={} name={} job={}/>))
                - 하위 컴포넌트(Card)에서는 props 라이브러리를 이용해서 추출하여 사용

                for user in users;
                 print(user["id"], user["name"], user["job"])
                 print(user.id, user.name, user.job)
            */}
            {users.map((user) => (
                <Card key={user.id} name={user.name} job={user.job} />
            ))}
        </div>
    );
}

export default ProfileList;