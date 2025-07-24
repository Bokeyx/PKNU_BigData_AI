// Import React
import React from "react";

// URL 패턴 처리를 위한 Router 라이브러리 import하기
// - Routes: root(/https://localhost:3000/) url 관리
//         : root를 줄여서 슬래시(/)로 표현함
// - Routes root(/https://localhost:3000/) 이하(/home) url 관리
import {Routes, Route} from "react-router-dom";

// 공통으로 사용하는 HTML 영역을 별도의 레이아웃으로 정의
import Layout from "./components/Layout"

// 페이지 컴포넌트 불러들이기
import Home from "./pages/Home";
import App5105 from "./pages/day51_05/App5105";
import App5106 from "./pages/day51_06/App5106";
import App5107 from "./pages/day51_07/App5107";
import BootstrapExample from "./pages/day51_bootstrap/BootstrapExample";

// BootStrap 적용하기
// - 설치 필요: npm install bootstrap
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <Routes>
      {/* 최초 서버 실행 시 보여지는 페이지: root 경로 */}
      <Route path="/" element={<Layout />}>
        {/* index 페이지 설정 
            : 최초 레이아웃에 보여질 페이지(index 페이지라고 칭함)
            : root(/) 경로는 모두 index 경로를 의미하겠다는 표현 */}
        <Route index element={<Home />}/>

        {/* /day51_05로 들어오는 URL은 App5105 컴포넌트 호출하기 */}
        <Route path="day51_05" element={<App5105 />}/>

        {/* /day51_06로 들어오는 URL은 App5106 컴포넌트 호출하기 */}
        <Route path="day51_06" element={<App5106 />}/>

        {/* /day51_07로 들어오는 URL은 App5107 컴포넌트 호출하기 */}
        <Route path="day51_07" element={<App5107 />}/>

        {/* /day51_bootstrap로 들어오는 URL은 BootstrapExample 컴포넌트 호출하기 */}
        <Route path="day51_bootstrap" element={<BootstrapExample />}/>
      </Route>
    </Routes>
  );
}

export default App;
