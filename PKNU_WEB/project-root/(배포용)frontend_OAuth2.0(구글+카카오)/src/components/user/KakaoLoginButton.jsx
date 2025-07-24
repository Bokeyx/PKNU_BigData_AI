// KakaoLoginButton.jsx
import React, { useEffect, useContext } from 'react';

import { AuthContext } from '../../pages/user/AuthContext';

const KakaoLoginButton = () => {
   const { login } = useContext(AuthContext);

  useEffect(() => {
    if (!window.Kakao.isInitialized()) {
      // JavaScript 키 정확히 입력
      window.Kakao.init("카카오톡 JavaScript Key 추가"); 
    }
  }, []);

  const loginWithKakao = () => {
    window.Kakao.Auth.login({
      success: (authObj) => {
        console.log("Kakao 로그인 성공:", authObj);

        // 사용자 정보 요청
        window.Kakao.API.request({
            url: "/v2/user/me",
            success: (res) => {
                console.log("사용자 정보 응답:", res);

                const kakaoAccount = res.kakao_account || {};
                const profile = kakaoAccount.profile || {}; // 사용자 정보가 포함되어 있음
                const email = kakaoAccount.email || "이메일 없음";
                const nickname = profile.nickname || "이름 없음";

                // useContext에 사용자 정보 저장(서버 전역에서 사용 가능)
                login({
                    provider: "kakao",
                    id: res.id,
                    name: nickname  || "이름 없음",
                    email: email || "이메일 없음",
                });

                alert(`로그인 성공: ${nickname} (${email})`);
            },
            fail: (error) => {
                console.error("사용자 정보 요청 실패", error);
            },
        });
      },
      fail: (err) => {
        console.error("Kakao 로그인 실패:", err);
      },
    });
  };

  // 버튼을 리턴함
  return (
    <button onClick={loginWithKakao}>
      🟡 카카오로 로그인
    </button>
  );
};

export default KakaoLoginButton;
