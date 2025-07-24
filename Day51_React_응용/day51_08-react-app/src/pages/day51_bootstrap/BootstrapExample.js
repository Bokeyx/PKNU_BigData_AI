import React from "react";
import UserCard from "./UserCard";

// 이미지 불러들이기
import hgdImg from "./images/hgd.jpg";
import kysImg from "./images/kys.jpg";
import lssImg from "./images/lss.jpg";

// 사용자 정보 배열 (목록 형태로 전달)
const users = [
  {
    name: "홍길동",
    email: "hong@example.com",
    image: hgdImg,
  },
  {
    name: "김유신",
    email: "kim@example.com",
    image: kysImg,
  },
  {
    name: "이순신",
    email: "park@example.com",
    image: lssImg,
  },
];

function BootstrapExample() {
  return (
    <div className="container mt-4">
      <h2>사용자 카드 목록 (Bootstrap)</h2>

      <div className="row">
        {/* 사용자 배열을 순회하면서 UserCard 컴포넌트를 출력
            - index : 반복시 1부터 자동 증가값을 만들어 냅니다. 
        */}
        {users.map((user, index) => (
          <div className="col-md-4 mb-3" key={index}>
            {/* props 방식으로 UserCard에 데이터 전달하기 */}
            <UserCard name={user.name} email={user.email} image={user.image} />
          </div>
        ))}
      </div>
    </div>
  );
}

export default BootstrapExample;
