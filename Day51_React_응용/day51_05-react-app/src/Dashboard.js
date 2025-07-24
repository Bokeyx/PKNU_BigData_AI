// React와 useContext 라이브러리 불러들이기
import React, {useContext} from "react";

// 사용자 상태 정보를 담고 있는 공간 정보 Context 컴포넌트 불러들이기
import AuthContext from "./AuthContext";

function Dashboard() {
    // 로그인한 사용자 정보(name, email)를 공간 정보에서 가지고 오기
    const {user} = useContext(AuthContext);

    return(
        <section>
            <h2>로그인 되었습니다. ::: Dashboard</h2>
            <p>* 이름: {user.name}</p>
            <p>* 이메일: {user.email}</p>
        </section>
    );
};

export default Dashboard;