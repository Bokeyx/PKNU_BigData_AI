// React import 하기
import React from "react";

function UserProfile(props) {
    return(
        <div>
            <h2>사용자 정보</h2>
            <p>
                * 이름: {props.name}
            </p>
            <p>
                * 나이: {props.age}살
            </p>
        </div>
    );
};

export default UserProfile;