import React, { useContext } from 'react';
import { GoogleLogin } from '@react-oauth/google';
import { AuthContext } from '../../pages/user/AuthContext';


const GoogleLoginButton = () => {
  const { login } = useContext(AuthContext);

  // 사용자가 정의한 버튼을 리턴함
  return (
    <GoogleLogin
      onSuccess={(credentialResponse) => {
        const token = credentialResponse.credential;

        // ✅ JWT Payload UTF-8 디코딩 (한글 깨짐 방지 처리)
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const binary = atob(base64);
        const bytes = new Uint8Array([...binary].map((char) => char.charCodeAt(0)));
        const decoded = JSON.parse(new TextDecoder().decode(bytes)); // ✅ UTF-8 적용

        // useContext에 사용자 정보 저장(서버 전역에서 사용 가능)
        login(decoded);
      }}
      onError={() => {
        console.log('Google 로그인 실패');
      }}
    />
  );
};

export default GoogleLoginButton;