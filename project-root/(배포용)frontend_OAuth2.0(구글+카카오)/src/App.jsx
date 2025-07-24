// React 라이브러리를 불러옵니다.
import React, { useContext } from 'react';
import { BrowserRouter } from 'react-router-dom';    // 브라우저 기반 라우팅 설정

import HomeRoutes from './routes/HomeRoutes';        // HomePage 관련 라우팅 모듈 불러오기
import CartRoutes from './routes/CartRoutes';        // Cart 관련 라우팅 모듈 불러오기
import BoardRouters from './routes/BoardRouters';    // Board 관련 라우팅 모듈 불러오기
// ML 관련 라우터 포함
import MLRouters from './routes/MLRouters'; 


/* ################################################################ */
/*  OAuth2.0 */
/* ################################################################ */
import GoogleLoginButton from './components/user/GoogleLoginButton';
import KakaoLoginButton from './components/user/KakaoLoginButton';
import { AuthProvider, AuthContext } from './pages/user/AuthContext';
import LogoutButton from './components/user/LogoutButton';

import { GoogleOAuthProvider } from '@react-oauth/google';
/* ################################################################ */


// 실제 화면을 구성하는 컴포넌트
const Home = () => {
  // AuthContext : 전역적으로 사용가능하며, 로그인 사용자 정보(이름, 이메일)를 가지고 있음
  //  - 다른 페이지에서 로그인 정보 확인을 위해서는 아래 코드를 이용해서 조건처리 하면됨 
  const { user } = useContext(AuthContext);

  return (
    <div style={{ padding: '30px' }}>

      {/* 사용자 정보가 있는 경우(로그인 성공인 경우..) */}
      {user ? (
        <>
          <p>로그인됨: {user.name} ({user.email})</p>
          <img src={user.picture} alt="프로필" width="100" />
          <p>로그인 방식: {user.provider}</p>
          <LogoutButton />

          <hr/>
            <BrowserRouter>

              {/* 최초 Index Page 관련 라우트 모듈 삽입(라우트 별도 관리 파일) */}
              <HomeRoutes />

              {/* Cart 관련 라우트 모듈 삽입(라우트 별도 관리 파일) */}
              <CartRoutes />

              {/* Board 관련 라우트 모듈 삽입(라우트 별도 관리 파일) */}
              <BoardRouters />

              {/* ML 관련 라우트 모듈 삽입(라우트 별도 관리 파일) */}
              <MLRouters />

            </BrowserRouter>
        </>
      ) : (

        // 사용자 정보가 없는 경우(로그인이 안된 경우..)
        <>
          <GoogleLoginButton />
          <KakaoLoginButton />
        </>
      )}
    </div>
  );
};

// 최상위 App 컴포넌트 정의
const App = () => {
  
  return (
    <div style={{ padding: '50px' }}>
      <h1>Google 로그인 테스트</h1>
       {/* 구글 인증을 위햇 전체 애플리케이션을 라우터로 감싸기
          - 클라이언트 ID 넣기 */}
      <GoogleOAuthProvider clientId="구글 클라이언트 ID 추가">
        {/* AuthContext.jsx에서 구현한 -> 사용자 정의 상태 관리 훅(Hoc) */}
        <AuthProvider>
          {/* AuthContext.jsx에서 children(하위 컴포넌트)으로 처리됨
              하위 컴포넌트에서는 로그인 사용자 정보를 공유 받아서 사용 가능 */}
          <Home />
        </AuthProvider>
      </GoogleOAuthProvider>
    </div>
      
  );
};

export default App;