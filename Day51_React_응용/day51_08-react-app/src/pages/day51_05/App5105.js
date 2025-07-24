// React 및 사용자 상태 저장관리(Hook 기능) 라이브러리 import
//  - useState: 상태 데이터를 저장하기 위한 라이브러리
//   -> 외부 컴포넌트로 상태 데이터를 공유 가능
import React, {useState} from "react";

// 로그인 상태를 담당하는 컴포넌트 import
import AuthContext from "./AuthContext";

// 로그인/로그아웃 상태 관리를 위한 헤더 컴포넌트 import
import Header from "./Header";

// // 로그인 폼 페이지 import
import LoginForm from "./LoginForm";

// // 로그인 이후 보여질 페이지 import
import Dashboard from "./Dashboard"

function App5105() {
  // 사용자 상태 정보 최초 데이터 설정하기
  // - 최초 사용자 상태는 없는 상태: null
  // - 사용 문법
  //  * [사용 할 Key, 값을 저장하기 위한 함수(set으로 시작, 변수명 첫자 대문자)]
  //  * 데이터 저장 방법: setUser({key : value}) or setUser(value) -> {user : value}
  //  * 데이터 추출 방법: user.key               or user
  const [user, setUser] = useState(null);
  return (
    // AutoContext.Provider
    //  : 다른 컴포넌트에 사용자 상태(user)데이터와 설정(setUser) 함수를 공유함
    //  : 다른 컴포넌트의 정의 - AutoContext.Provider 태그 낸에서 사용되는 컴포넌트들을 의미함
    <AuthContext.Provider value={{user, setUser}}>
      <div>
        {/* 로그인/로그아웃 상태 처를 위한 Header 컴포넌트 보여주기 */}
        <Header/>

        {/** 사용자 상태 데이터에 따라 컴포넌트를 선별하여 보여주기: 3항 연산자 사용
             - 3항 연산자: 파이썬에는 없으며, 이외 프로그램 언어에 있는 연산자
             - 3항 연산자 문법: 조건 ? 참일 때 처리 값: 거짓일 때 처리값

             - user의 값이 있으면 True: Dashboard 페이지 보여주기
             - user의 값이 있으면 False: LoginForm 페이지 보여주기
        */}

        {user ? <Dashboard/> : <LoginForm/> }
      </div>
    </AuthContext.Provider>
  );
}

export default App5105;
