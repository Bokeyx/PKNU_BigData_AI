// import React
import React from "react";

// 라우트 URL에 따라서 페이지 출력 위치 컨트롤
// - Outlet: 하위 라우트에 대해서 -> 별도 출력 위치를 지정하겠다는 의미
import {Outlet} from "react-router-dom";

// Header 정보 가지고 오기
import Header from "./Header";

// css 스타일 적용을 위해 외부 파일 불러들이기
import style from "../css/layout.module.css"

function Layout() {
    return(
        <div className={style.wrapper}>
            {/* Header 정보 출력하기 */}
            <Header />

            {/* 하위 라우터로 들어오는 모든 페이지는 이곳에 출력하기 */}
            <main className={style.mainContent}>
                <Outlet />
            </main>
        </div>
    );
}

export default Layout;