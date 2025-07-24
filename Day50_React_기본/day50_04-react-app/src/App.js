// React import 하기
import React from "react"

// 하위 컴포넌트 import 하기: ProfileList.js
import ProfileList from "./ProfileList"

function App() {
  return (
    <div>
      <h1>하위 컴포넌트: ProfileList 포함시키기</h1>
      <ProfileList />
    </div>
  );
}

export default App;
