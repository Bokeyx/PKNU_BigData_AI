import React from "react";

// props를 통해 사용자 정보를 전달받아 카드 형태로 출력하는 컴포넌트
function UserCard({ name, email, image }) {
  return (
    <div className="card" style={{ width: "18rem" }}>
      {/* 사용자 이미지 */}
      <img src={image} className="card-img-top" alt="사용자 이미지" />

      {/* 카드 본문 */}
      <div className="card-body">
        {/* 사용자 이름 */}
        <h5 className="card-title">{name}</h5>

        {/* 사용자 이메일 */}
        <p className="card-text">{email}</p>

        {/* 링크 처리 */}
        <a href={`mailto:${email}`} className="btn btn-primary">
          이메일 보내기
        </a>
      </div>
    </div>
  );
}

export default UserCard;
