# Spring 뉴스피드 프로젝트


## 💸 SalaryOneTrillion-Movie-review-newsfeed 

---
# 💰 API 명세
https://documenter.getpostman.com/view/30857800/2s9YeEareR

---
# 💶 ERD 
![20231127_031648](https://github.com/SalaryOneTrillion/SalaryOneTrillion-Movie-review-newsfeed/assets/123870616/21b9bf51-4f8c-4b42-9657-90bd53f17d11)


---

# 💷 프로젝트 간단 소개
- 저희 연봉 1조는 영화 리뷰 뉴스피드를 제작하였습니다. 
- 영화진흥위원회에서 오픈 API를 파싱해서 Movie Entity와 매핑을 하여 실제 영화 정보들을 데이터베이스에 저장하도록 구현하였습니다.
- 필수 구현 기능인 사용자 인증 기능, 프로필 관리, 게시물 CRUD, 뉴스피드 기능과 추가 구현 기능인 댓글 CRUD 기능을 구현하였습니다.
- enum클래스와 @ExceptionHandler, @RestControllerAdvice 어노테이션을 사용하여 enum안에 각 클래스에서 발생할 수 있는 오류들의 값들이 담겨 있기 때문에 하나의 핸들러로 모든 비지니스 예외처리가 가능합니다.
- 

---
# 💵 필수 구현 기능
- [x]  **사용자 인증 기능**

    - 회원가입 기능
        - 새로운 사용자가 ID와 비밀번호의 형태로 서비스에 가입할 수 있어야 합니다.
            - 이 때, 비밀번호는 안전하게 암호화되어 저장되어야 합니다!
    - 로그인 및 로그아웃 기능
        - 사용자는 자신의 계정으로 서비스에 로그인하고 로그아웃할 수 있어야 합니다.
      

- [x]  **프로필 관리**
    - 프로필 수정 기능
        - 이름, 한 줄 소개와 같은 기본적인 정보를 볼 수 있어야 하며 수정할 수 있어야 합니다.
        - 비밀번호 수정 시에는 비밀번호를 한 번 더 입력받는 과정이 필요합니다.


- [x]  **게시물 CRUD 기능**
    - 게시물 작성, 조회, 수정, 삭제 기능
        - 게시물 조회를 제외한 나머지 기능들은 전부 인가(Authorization) 개념이 적용되어야 하며 이는 JWT와 같은 토큰으로 검증이 되어야 할 것입니다.
        - 예컨대, 내가 작성한 글을 남이 삭제할 수는 없어야 하고 오로지 본인만 삭제할 수 있어야겠죠?
    
    - 게시물 작성, 수정, 삭제 시 새로고침 기능
        - 프론트엔드에서 게시물 작성, 수정 및 삭제를 할 때마다 조회 API를 다시 호출하여 자연스럽게 최신의 게시물 내용을 화면에 보여줄 수 있도록 해야 합니다!


- [x]  **뉴스 피드 기능**
    - 뉴스 피드 페이지
        - 사용자가 다른 사용자의 게시물을 한 눈에 볼 수 있는 뉴스 피드 페이지가 있어야 합니다.

---
# 💴 추가 구현 기능
- [x]  **댓글 CRUD 기능**
    - 댓글 작성, 조회, 수정, 삭제 기능
        - 사용자는 게시물에 댓글을 작성할 수 있고 본인의 댓글은 수정 및 삭제를 할 수 있어야 합니다.
        - 또한, 게시물과 마찬가지로 댓글 조회를 제외한 나머지 기능들은 인가(Authorization)개념이 적용되어야 합니다.
    - 댓글 작성, 수정, 삭제 시 새로고침 기능
        - 프론트엔드에서 댓글 작성, 수정 및 삭제를 할 때마다 조회 API를 다시 호출하여 자연스럽게 최신의 댓글 목록을 화면에 보여줄 수 있도록 해야 합니다!
