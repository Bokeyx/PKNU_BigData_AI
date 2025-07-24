// React 및 Context 생성을 위한 라이브러리 import
// - useContext: AutoContext의 정보를 담아 놓는 공간
import React, {useContext} from "react";


// 사용자 상태 정보값을 가지고 있는 AuthoContext import
import AuthContext from "./AuthContext";

//함수 만들기
function Header () {
    /**
     * AuthContext 내에 저장되어 있는 사용자 상태정보(user, setUser) 받아오기
     * - useContext(AutoContext): AuthContext의 공간 정보를 useContext에 담아서 사용자 정보 추출
    */
    const {user, setUser} = useContext(AuthContext);

    /**
     * [로그아웃] 버튼 클릭 시 호출되는 함수 정의
     * - JSX 함수 정의 문법: const handleLogout = (매개변수) => {처리 로직};
    */
    const handleLogout = () => {
        // 사용자 상태 정보값을 null로 변경
        setUser(null);
    };

    return(
        <header>
            <h1>로그인/로그아웃 상태 관리 ::: Header.js</h1>
            <hr/>
            {/** 사용자 로그인 상태 처리: 3항 연산자 사용
                 - user의 상태값이 null이 아니면: 환영합니다. 메시지 보여주기
                                               : [로그아웃] 버튼 보여주기
                 - user의 상태값이 null이면: 로그인 해주세요. 메시지 보여주기
            */}
            {
                user ?
                (
                    <div>
                        <span>(홍길동 님)환영합니다.</span>
                        {/* 로그아웃 버튼 */}
                        <button onClick={handleLogout}>로그아웃</button>
                    </div>
                ) : (
                    <span>로그인 해주세요!</span>
                )
            }
        </header>
    );
}

export default Header;