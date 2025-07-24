import React, {useState} from "react";

function CommentBoard () {
    // 댓글 하나만 저장하는 상태 값: 문자열 타입으로 정의
    const[comment, setComment] = useState("");

    // 댓글 여러개를 저장하는 상태값: 배열 타입으로 정의
    const [comments, setComments] = useState([]);

    const handleSumbit = (e) => {
        // 서식 검증
        e.preventDefault();

        /**
         * 문자열이 입력되었는지와 타입이 같은지 확인
         *  - trim(): 제일 왼쪽과 오른쪽의 공백 제거
         *  - === 의 의미: 값이 없거나 타입이 문자열이 아닌 경우 return 시킴
         *               : 값 입력 여부와 동일 타입 여부를 판단하는 비교연산자 
         */
        if(comment.trim() === "") return;

        // alert(`호출...`);
        // 하나의 댓글을 여러개의 댓글을 관리하는 상태 변수에 담기
        // ...의 의미: comments 배열의 마지막에 comment의 값을 넣으라는 의미
        //           : 파이썬에서 list에 값 추가할 때 사용하는 append()함수와 동일함
        setComments([...comments, comment]);

        // 댓글 하나를 저장하는 상태 변수값은 다시 비우기(초기화)
        setComment("");
    };

    const handleDelete = (indexToDelete) => {
        // filter를 이용해 index가 일치하지 않는 항목들만 남김
        const updatedComments = comments.filter((_, index) => index !== indexToDelete);
        setComments(updatedComments);
    };

    return(
        <form onSubmit={handleSumbit}>
            {/* 댓글 입력 폼 */}
            <div>
                <input type="text" value={comment}
                       placeholder="댓글을 입력해 주세요"
                       required
                       onChange={(e) => setComment(e.target.value)}/>
                       <button type="submit">댓글 등록</button>

                {/* comments에 담겨 있는 여러 댓글을 출력하기 
                - 배열에 담겨 있기에 반복을 통해 출력시킴*/}
                <ul>
                    {
                        // index: map이 반복하면서 발생하는 번호값(고유 식별자 값)
                        comments.map((item, index) => (
                            // map 내에 결과 출력
                            <li key={index}>{item}</li>
                        ))
                    }
                </ul>

                {/* 삭제 */}
                <ul>
                    {
                        comments.map((item, index) => (
                            <li key={index}>
                                {item}
                                <button type="button" onClick={() => handleDelete(index)}>
                                    삭제
                                </button>
                            </li>
                        ))
                    }
                </ul>
            </div>
        </form>
    );
};

export default CommentBoard;