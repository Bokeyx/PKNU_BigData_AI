import React from 'react';
import ReactDOM from 'react-dom/client';

// 페이지에서 라우터 기능 사용을 위해 라이브러리 추가
import {BrowserRouter} from 'react-router-dom';
import App from './App';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>
);
