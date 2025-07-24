/**
 * Context 생성을 위한 함수 import
 * - createContext: 외부 컴포넌트에서 데이터를 자유롭게 사용할 수 있도록 공유하는 라이브러리
 * - 사용자 상태 정보(useState)를 담아서 공유하는 사용됨
 * - 여러 개의 상태 정보(useState)를 담을 수 있음 
*/
import {createContext} from "react";

/**
 * 로그인 상태 정보를 담기(setState) 위해 공유하는 Context 생성
*/
const AuthContext = createContext(null);

/**
 * 외부 컴포넌트에서 사용할 수 있도록 export 처리
*/
export default AuthContext;