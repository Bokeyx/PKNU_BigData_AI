/* 자바스크립트 프로그램은 이 공간에 합니다. */
// 함수정의하기
function goChangeMessage(msg) {
    // 함수 잘 호출되는지 먼저 확인하기
    // alert("goChangeMessage() 함수 호출 잘 됨!!!");

    // 매개변수로 받은 값을 팝업창에 출력해 보기..(잘 받아오는지 먼저 확인)
    // alert(msg);

    // p태그 중에 id가 p1인 태그의 정보 가지고 오기
    // 이 문서 안에서(document), id가 p1인 값을 가지는 태그의 정보 가지고 오기
    /*
        (변수 선언 방법)
            - 변수명
            - 변수명 = "문자열값" or 1 or true or {key : value} or [1, 2, 3]
            (변수명 왼쪽에 아무것도 제시하지 않아도 무방함. 지향하지는 않습니다.)

            - var 변수명
            - var 변수명 = "문자열값" or 1 or true or {key : value} or [1, 2, 3]
            (var은 자바스크립트 초기에 사용하던 명령, 내부 버그 문제로 사용 잘 안함)

            - let 변수명
            - let 변수명 = "문자열값" or 1 or true or {key : value} or [1, 2, 3]     
            (let는 var의 버그를 해소하기 위해 사용됨, 현재 let으로 사용되고 있음)  

            - const 변수명 = "문자열값" or 1 or true or {key : value} or [1, 2, 3]
            (const는 상수라고 칭하며, 최초 선언과 값의 정의를 동시에 해야함
                이후 값의 수정은 불가함, 
                단, 오브젝트(딕셔너리)의 경우 값(value) 수정만 가능)
    */
    // let p1Tab = document.getElementById("p1");
    // p1Tab 태그 내에 텍스트 정보를 수정
    // innerHTML : 해당 태그와 태그 사이에 값(html 태그, 스타일, js 등) 사용 가능
    // p1Tab.innerHTML = "<b>임의의 값 넣기!!</b> : " + msg;

    /*
        1. p1버튼 클릭 시
            - id가 p1인 태그 사이의 데이터를 "p1이 클릭되었습니다!"로 내용 변경

        2. p2버튼 클릭 시
            - id가 p2인 태그 사이의 데이터를 "p2가 클릭되었습니다!"로 내용 변경
    */

    document.getElementById(msg).innerHTML = msg + "이(가) 클릭되었습니다!";

}