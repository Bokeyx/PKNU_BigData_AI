/**
 * React 라이브러리, useState 사용자 상태관리, useContext 사용자 정보 담아놓는 공간 라이브러리 import
 * - {useState, useContext}이 라이브러리를 Hook 이라고 칭합니다.
 * - 웹 서버 공간에서 데이터 처리 시 중간 중간 개입하는 라이브러리
*/ 
import React, {useState, useContext} from "react";

// 로그인 상태 정보 공간을 관리하는 컴포넌트 import
import AuthContext from "./AuthContext";

function LoginForm() {
    // 사용자 상태 데이터 가지고 오기 (setUser 정보만 가지고 오기)
    const {setUser} = useContext(AuthContext);

    // 이름 데이터를 상태 라이브러리를 통해서 저장하기
    const [name, setName] = useState("");

    // 이멤일 데이터를 상태 라이브러리를 통해서 저장하기
    const [email, setEmail] = useState("");

    //onSubmit에 대한 함수 정의하기
    const handleSumbit = (e) => {
        // 서식 검증하기
        // 서식 검증하기: input 태그의 type에 따라 서식이 적용되는 type인 경우 해당됨
        e.preventDefault();

        // 사용자가 입력한 name과 email을 setUser 함수를 통해 user 상태 정보에 저장하기
        setUser({name, email});
    };

    return(
        /**
         * 사용자가 이름과 이메일을 입력한 후 [저장] 버튼을 클릭하면
         * - 사용자 상태 정보를 신규로 생성: name, email 정보 생성
         *  -> name, setName
         *  -> email, setEmail
         * - 사용자 신규 상태 정보를 setUser 함수를 이용해서 user 상태정보에 담기
         */
        <form onSubmit={handleSumbit}>
            {/** 이름 입력 폼 만들기 */}
            <div>
                <label>* 이름: </label>
                <input type="text" value={name}
                       placeholder="이름을 입력해 주세요"
                       required
                       // text 박스의 value의 값이 변경 될 때마다
                       // - 입력된 값을 name 상태 정보에 저장하기(setName()함수 처리)
                       // - 위에 name 관련 상태 정보(useState) 생성해야함
                       // - (e): e는 event의 약자 -> 현재 위치의 태그정보를 의미함
                       // - onChange 이벤트 발생 시 함수를 호출하게 됨
                       //  -> 별도의 함수 호출 없이, 함수를 직접 정의 -> (e)
                       //   -> 처리는 => 입력된 값을 name 상태 정보에 저장하는 처리 수행
                       onChange={(e) => setName(e.target.value)}/>
            </div>
            {/** 이메일 입력 폼 만들기 */}
            <div>
            <label>* 이메일: </label>
                <input type="email" value={email}
                       placeholder="이메일을 입력해 주세요"
                       required
                       // text 박스의 value의 값이 변경 될 때마다
                       // - 입력된 값을 email 상태 정보에 저장하기(setEmail()함수 처리)
                       // - 위에 email 관련 상태 정보(useState) 생성해야함
                       // - (e): e는 event의 약자 -> 현재 위치의 태그정보를 의미함
                       // - onChange 이벤트 발생 시 함수를 호출하게 됨
                       //  -> 별도의 함수 호출 없이, 함수를 직접 정의 -> (e)
                       //   -> 처리는 => 입력된 값을 email 상태 정보에 저장하는 처리 수행
                       onChange={(e) => setEmail(e.target.value)}/>
            </div>

            {/* 로그인 버튼 생성하기 */}
            <button type="submit">로그인</button>
        </form>
    );
}

export default LoginForm;