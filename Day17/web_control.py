from selenium import webdriver

import pandas as pd

from selenium.webdriver.common.by import By

import time

class WebControl:
    def __init__(self, driver=None):
        if driver is None:
            self.driver = None
        else:
            self.driver = driver

    def see_more_all(self):
        while True:
            try:
                # '더보기' 버튼을 찾아 클릭
                see_more = "#btnAddMovie"
                see_more_element = self.driver.find_element(By.CSS_SELECTOR, see_more)
                see_more_element.click()
                
                # 브라우저에서 클릭 후 로딩하는 시간 동안 대기
                time.sleep(1)
                print("더보기 버튼 클릭 후 대기 중...")
                
            except :
                print(f"[더보기] 버튼이 더 이상 보이지 않습니다.")
                break

    # [실관람평] 탭 클릭을 위한 태그 정보 클릭하는 함수
    def audience_e(self):
        try :
            movie_tab_path = "#contentData > div:nth-child(5) > div.tab-list.fixed > ul"
            movie_tab_element = self.driver.find_element(By.CSS_SELECTOR, movie_tab_path)
            ### 더보기 클릭
            movie_tab_element.click()
                    
        except :
            print("[더보기] 버튼이 더 이상 보이지 않습니다.")

    # 새로운 윈도우로 전환하는 함수
    def setWindowPage(self):
        page_handle = self.driver.window_handles[-1]
        self.driver.switch_to.window(page_handle)
        time.sleep(1)

    # 이전 페이지로 돌아가는 함수
    def go_back(self):
        self.driver.execute_script("window.history.go(-1)")
        # - 이전페이지 로딩되는 시간동안 프로그램 실행 잠시 대기
        time.sleep(1)
        print("이전페이지로 돌아갑니다.")

    # 영화 클릭하기
    def click_movie_detail(self, movie_index=0):
        try:
            # 1. 영화 목록 전체 정보를 추출
            ### 영화 목록 전체 가지고 오기 위한 태그 경로 정의
            self.movie_list_path = "div.movie-list-info"
            
            ### 태그 경로에 대한 태그 정보 조회하기
            self.movie_elements = self.driver.find_elements(By.CSS_SELECTOR, self.movie_list_path)
            
            ### 무비 상세 페이지 클릭하기
            if self.movie_elements:
                if movie_index < len(self.movie_elements):
                    # 선택된 인덱스의 영화 클릭
                    self.movie_elements[movie_index].click()
                    print(f"영화 상세 페이지로 이동: 영화 {movie_index + 1}")
                else:
                    print(f"잘못된 인덱스 번호: {movie_index}. 해당 영화가 목록에 없습니다.")
            else:
                print("영화 목록을 찾을 수 없습니다.")
                
        except :
            print(f"영화 상세 페이지 클릭 중 오류 발생")

    # 크롬 브라우저를 열고 URL을 요청하는 함수
    def getDriver(self, url):
        # 크롬 드라이버 경로 (필요 시 드라이버 경로 지정)
        self.driver = webdriver.Chrome()  # 드라이버 경로가 환경 변수에 설정되어 있으면 이렇게 사용
        self.driver.get(url)  # URL을 로딩

        # 페이지가 완전히 로딩될 때까지 잠깐 대기
        time.sleep(1)  # 페이지 로딩 대기 시간 (3초)
        print("크롬 브라우저를 실행합니다.")
        
        return self.driver

    # 크롬 브라우저 종료
    def shutDriver(self):
        if self.driver:
            self.driver.quit()  # 드라이버가 존재하면 종료
        else:
            print("드라이버가 실행 중이 아닙니다.")


    def title(self, title_path):
        ### 영화 상세 정보 추출하기
        self.title_path = title_path
        # 영화제목이 있는 태그 정보 추출하기
        title_element = self.driver.find_element(By.CSS_SELECTOR, self.title_path)
        # 추출한 태그에서 영화제목 text 추출하여 출력하기
        title = title_element.text
        return title

    def score(self, score_path):
        try:
            score_element = self.driver.find_element(By.CSS_SELECTOR, score_path)
            # - 실관람평 텍스트 추출하기
            if score_element:
                # 관람평이 있을 경우, 해당 인덱스의 텍스트 추출
                score = score_element.text
            else:
                # 관람평이 없을 경우 0 처리
                score = 0
        except:
            score = 0

        return score

        
    def temp(self, temp_path):
        try:
            ### [예매순위] 및 [예매율] 추출하기
            temp_element = self.driver.find_element(By.CSS_SELECTOR, temp_path)
            
            # 요소가 있으면 텍스트를 반환
            temp_text = temp_element.text.split(" ")
            rate = temp_text[1][1:][:-2]
            ranking = temp_text[0][:-1]
            
        except :
            # 예외가 발생하면 0 처리
            rate = 0
            ranking = 0
            
        return rate, ranking


    def audience(self, audience_path):
        try:
            ### [누적관객수] 추출하기
            audience_element = self.driver.find_element(By.CSS_SELECTOR, audience_path)
            
            audience = audience_element.text.replace(',', '')
            
        except:
            audience = 0
        
        return audience

    def use_score(self, use_score_path):
        try:
            # - 개별관람평점이 있는 모든 태그 정보 추출하기
            use_score_elements = self.driver.find_elements(By.CSS_SELECTOR, use_score_path)
        
            use_scores = []
            if use_score_elements:
                for i in range(len(use_score_elements)):
                    # - 각 태그의 텍스트 값 추출하여 출력해보기
                    use_scores.append(use_score_elements[i].text)
            # 점수가 없을 경우 빈 리스트로 반환 (0은 포함하지 않음)
            return use_scores if use_scores else None

        except:
            return None  # 오류가 발생하면 None 반환
        
    def use_comment(self, use_comment_path):
        try:
            # - 개별관람평내용 있는 모든 태그 정보 추출하기
            use_comment_elements = self.driver.find_elements(By.CSS_SELECTOR, use_comment_path)
        
            use_comments = []
            if use_comment_elements:
                for c in range(len(use_comment_elements)):
                    # - 각 태그의 텍스트 값 추출하여 저장
                    use_comments.append(use_comment_elements[c].text)
            return use_comments if use_comments else None  # 점근 내용이 없으면 None 반환

        except :
            return None  # 오류가 발생하면 None 반환

    def pos_neg(self, pos_neg_path):
        try:
            # - 개별관람평점이 있는 모든 태그 정보 추출하기
            pos_neg_elements = self.driver.find_elements(By.CSS_SELECTOR, pos_neg_path)
        
            positive_count = 0  # 초기화
            negative_count = 0  # 초기화

            # 개별 관람평점이 존재할 경우
            if pos_neg_elements:
                for i in range(len(pos_neg_elements)):
                    # - 각 태그의 텍스트 값을 숫자로 변환하고 평가
                    pos_neg = pos_neg_elements[i].text
                    try:
                        pos_neg = float(pos_neg)  # 점수를 숫자 형태로 변환
                        if pos_neg >= 6:
                            positive_count += 1
                        else:
                            negative_count += 1
                    except ValueError:
                        print(f"정의 값에 문제가 있습니다. {i + 1}: {pos_neg}")
            else:
                print("유저의 평가가 없습니다.")

        except :
            print("긍정/부정 평가 로직에서 오류 발생")
            
        return positive_count, negative_count

    # def use_score(self, use_score_path):
    #     try :
    #         # - 개별관람평점이 있는 모든 태그 정보 추출하기
    #         use_score_elements = self.driver.find_elements(By.CSS_SELECTOR, use_score_path)
        
    #         use_scores = []
    #         if use_score_elements:
    #             for i in range(len(use_score_elements)):
    #                 # - 각 태그의 텍스트 값 추출하여 출력해보기
    #                 use_scores.append(use_score_elements[i].text)
    #         else:
    #             use_scores.append(0)

    #     except:
    #         use_scores.append(0)
        
    #     return use_scores

    # def use_comment(self, use_comment_path):
    #     try :
    #         # - 개별관람평내용 있는 모든 태그 정보 추출하기
    #         use_comment_elements = self.driver.find_elements(By.CSS_SELECTOR, use_comment_path)
        
    #         use_comments = []
    #         if use_comment_elements:
    #             for c in range(len(use_comment_elements)):
    #                 # - 각 태그의 텍스트 값 추출하여 저장
    #                 use_comments.append(use_comment_elements[c].text)
    #         else:
    #             use_comments.append(0)

    #     except :
    #         use_comments.append(0)
            
    #     return use_comments

    # def pos_neg(self, pos_neg_path):
    #     try : 
    #         # - 개별관람평점이 있는 모든 태그 정보 추출하기
    #         pos_neg_elements = self.driver.find_elements(By.CSS_SELECTOR, pos_neg_path)
        
    #         positive_count = 0  # 초기화
    #         negative_count = 0  # 초기화
    #         # 개별 관람평점이 존재할 경우
    #         if pos_neg_elements:
    #             for i in range(len(pos_neg_elements)):
    #                 # - 각 태그의 텍스트 값을 숫자로 변환하고 평가
    #                 pos_neg = pos_neg_elements[i].text
    #                 try:
    #                     pos_neg = float(pos_neg)  # 점수를 숫자 형태로 변환
    #                     if pos_neg >= 6:
    #                         positive_count += 1
    #                     else:
    #                         negative_count += 1
    #                 except ValueError:
    #                     print(f"정의 값에 문제가 있습니다. {i + 1}: {pos_neg}")
    #         else:
    #             print("유저의 평가가 없습니다.")

    #     except :
    #         print(f"정의 값에 문제가 있습니다. {i + 1}: {pos_neg}")
            
    #     return positive_count, negative_count

