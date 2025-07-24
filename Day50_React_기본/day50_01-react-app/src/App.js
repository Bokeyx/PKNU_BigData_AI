// 페이지에 표시할 로고 이미지 불러들이기
// from 뒤에 파일명이 있는 경우 import 뒤에는 이름으로 사용됨
import logo from './logo.svg';
// App.css 스타일을 불러들임
// from 없이 import만 있는 경우에는 파일의 코드 자체를 불러들이는 방식임
import './App.css';

// 응답할 기능을 처리한 결과를 처리하는 함수
// APP 컴포넌트라고 칭함
// - 컴포넌트들은 함수로 정의되며,
// - 결과값을 리턴하여 외부에서 해당 .js 파일을 import하여 사용하게됨
// - import하는 곳에서는 return된 코드를 불러들여서 사용하면 됨
function App() {
  // - 컴포넌트는 항상 리턴으로 반환해야함(외부에서 사용해야 하기 때문에)
  // - 리턴되는 타입은 JSX 문법으로 작성함(HTML태그 + 변수명 + XML 등)
  // - JSX 문법을 이용해서 HTML 코드 내에 프로그램(변수, 반복문 등)을 넣을 때는
  //  -> 중괄호{}를 이용함, 이때 중괄호는 프로그램 영역을 의미하며,
  //     json(딕셔너리, 오브젝트)를 의미하지는 않음
  // - JSX 코드를 이용하는 경우, HTML에서는 class 속성은
  //  -> className이라는 속성으로 사용됨
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

// 해당 컴포넌트 파일의 결과를 export하여, index.js에서 import하여 사용할 수 있도록 함
//  - index.js에서 import하여 자동으로 불러들여서 사용할 수 있도록 함
//  - index.js 뿐만 아니라, 외부 파일에서도 import로 불러들여서 사용가능
export default App;
