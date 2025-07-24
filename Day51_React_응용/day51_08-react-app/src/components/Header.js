// import React
import React from "react";

// 링크 처리를 위한 라이브러리 import 하기
// : 클릭하는 링크는 Router와 연결하여 사용됨

import {Link} from "react-router-dom";

// 스타일 적용하기
import style from "../css/layout.module.css"

function Header () {

    return(
        /**
         * import style을 적용한 스타일: ../css.layout.module.css 사용
         */
        // <nav className={style.nav}>
        //     <ul>
        //         <li>
        //             {/* 클릭하면: App.js에서 url 패턴에 의해, 해당 페이지를 찾아서 처리 */}
        //             <Link to="/">[Home]</Link>
        //         </li>
        //         <li>
        //             {/* 클릭하면: App.js에서 url 패턴에 의해, 해당 페이지를 찾아서 처리 */}
        //             <Link to="/day51_05">[App5105]</Link>
        //         </li>
        //         <li>
        //             {/* 클릭하면: App.js에서 url 패턴에 의해, 해당 페이지를 찾아서 처리 */}
        //             <Link to="/day51_06">[App5106]</Link>
        //         </li>
        //         <li>
        //             {/* 클릭하면: App.js에서 url 패턴에 의해, 해당 페이지를 찾아서 처리 */}
        //             <Link to="/day51_07">[App5107]</Link>
        //         </li>
        //     </ul>
        // </nav>

        /**
         * BootStrap 코드 적용: index.html의 BootStrap CDN 방식 적용
         */
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div className="container-fluid">
                <Link className="navbar-brand" to="/">[Home]</Link>
                
                {/* 토글 버튼 추가 */}
                <button 
                className="navbar-toggler" 
                type="button" 
                data-bs-toggle="collapse" 
                data-bs-target="#navbarNav" 
                aria-controls="navbarNav" 
                aria-expanded="false" 
                aria-label="Toggle navigation"
                >
                <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav">
                    <li className="nav-item">
                    <Link className="nav-link" aria-current="page" to="/day51_05">[App5105]</Link>
                    </li>
                    <li className="nav-item">
                    <Link className="nav-link" aria-current="page" to="/day51_06">[App5106]</Link>
                    </li>
                    <li className="nav-item">
                    <Link className="nav-link" aria-current="page" to="/day51_07">[App5107]</Link>
                    </li>
                    <li className="nav-item">
                    <Link className="nav-link" aria-current="page" to="/day51_bootstrap">[BootstrapExample]</Link>
                    </li>
                </ul>
                </div>
            </div>
            </nav>
    ); 
};

export default Header;