// Import React & useState
import React, {useState} from "react";

function UserForm () {
    // Save Name Status
    const [name, setName] = useState("");
    // Save Email Status
    const [email, setEmail] = useState("");

    // Define onSubmit function
    // 함수(자바스크립트 함수)내에서 문자열 처리: 탭 위에 싱글 따옴표
    const handleSumbit = (e) => {
        e.preventDefault();
        alert(`이름: ${name} | 이메일: ${email}`);
        
    };
    return(
        <form onSubmit={handleSumbit}>
            <div>
                <label>* 이름: </label>
                <input type="text" value={name}
                       placeholder="이름을 입력해 주세요"
                       required
                       onChange={(e) => setName(e.target.value)}/>
            </div>
            <div>
                <label>* 이메일: </label>
                <input type="email" value={email}
                       placeholder="이메일을 입력해 주세요"
                       required
                       onChange={(e) => setEmail(e.target.value)}/>
            </div>
            <button type="submit">로그인</button>
        </form>
    );
};

export default UserForm;



