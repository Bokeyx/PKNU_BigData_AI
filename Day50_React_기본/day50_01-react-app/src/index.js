// React 라이브러리 불러오기(JSX 해석을 위해 필수)
// JSX: JavaScript XML (React에서 사용하는 프로그램 문법)
//      (XML: eXtensible Markup Language, 확장 마크업 언어, 일명 태그)
//      (HTML 태그는 의미있는 태그, XML 데이터 태그-의미없음)
//    : JavaScript 문법과 XML 문법을 동시에 사용가능
//    : JavaScript 프로그램에 XML(HTML + XML) 태그를 그대로 사용가능ㄴ
//    : 예시 - const tag = <h1>Hello, React!!</h1>
import React from 'react';
// React DOM 사용을 위한 라이브러리
import ReactDOM from 'react-dom/client';
// 외부 css 파일 불러들이기
import './index.css';
// 외부 App.js 파일 불러들이기(컴포넌트, 처리 기능 수행)
import App from './App';
// React의 로딩 속도, 반응속도 등과 같은 주요 성능 지표를 수집하는데 사용됨
// (형식적으로 import 또는 사용하지 않아도 됨)
import reportWebVitals from './reportWebVitals';

// *** (중요): public 폴더의 index.html에서 'root'라는 id를 가진
//             div 태그 정보를 가지고 옴(React뭄법을 적용하겠다는 의미)
//             또한, Root(최초 진입점)이라는 의미를 부여함
const root = ReactDOM.createRoot(document.getElementById('root'));

//div 태그 정보에 App.js의 처리결과를 합치기(렌더링)
//index.js 파일은 React에서 관리됨
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
