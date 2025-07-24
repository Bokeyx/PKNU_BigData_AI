// React import 하기
import React from "react";

// 컴포넌트는 함수로 정의 후 return 해야 합니ㅏㄷ.
// 컴포넌트 함수는 파일명과 동일하게 해야 하비낟.
function WelcomeMessage(){
    return(
        // 응답할 결과 태그 정의하기
        <div>
            <p>
                이곳은 하위 컴포넌트 입니다. : WelcomeMessage
            </p>
        </div>
    );
}

// 다른 외부 파일에서 import 할 수 있도록 export 합니다.
export default WelcomeMessage;