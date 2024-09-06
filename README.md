## ERD
<img width="701" alt="스크린샷 2024-09-06 오전 10 29 17" src="https://github.com/user-attachments/assets/ca6c3217-97ec-4d09-8b15-3df371973751">

## API
<img width="755" alt="스크린샷 2024-09-06 오전 10 30 21" src="https://github.com/user-attachments/assets/3d0f4c4a-b1af-4725-8813-ab243f13dded">

## 와이어 프레임
<img width="720" alt="스크린샷 2024-09-06 오전 10 55 03" src="https://github.com/user-attachments/assets/694741af-59af-407f-bbdc-bdc01aa7a65e">


## 트러블슈팅
### 1. 양방향 관계설정으로 인한 순환참조 오류
#### 오류 내용
PostMan으로 API Get요청시 Could not write JSON: Document nesting depth (1001) exceeds the maximum allowed (1000, from StreamWriteConstraints.getMaxNestingDepth())라는 오류가 콘솔에 출력되었고,
PostMan의 Response에도 같은 값이 계속해서 호출되었습니다.

#### 오류의 원인
UserEntity와 BoarEntity가 양방향 관계설정이 되어있었으며, 이로인해 1001길이를 초과하여 재귀적으로 호출되었습니다.

#### 오류 해결
Board에서 UserEntity를 참조하는 필드에 @JsonIgnore을 붙여 오류를 해결하였습니다.

### 2. 구독자의 게시물을 가져오지 못하는 오류
#### 오류 내용
다른 유저를 구독(follow)하였을 경우 해당유저의 게시글도 본인이 작성한 글을 불러올때 같이 불러오게 하는게 목표였습니다.
하지만, 본인의 게시물만 가져올뿐 구독자의 게시물은 가져오지 못했습니다.

#### 오류 원인
1. 작성된 코드에서는 구독자의 게시물을 찾을때, List Subscribe 타입으로 가져왔으나, List Board 타입이 더 적절하였습니다.
2. 처음 작성된 코드에서는 Repository에서 구독자의 게시글을 검색하는 메서드가 findByFriend 였지만, findBoardsByUserFriends가 더 적잘한 표현이였습니다.

 
#### 해결과정
1. 구독하는사람의 글을 불러오는 메서드를 수정하였지만 여전히 볼 수 없었습니다.
2. Subscribe Repository에 JPQL 서브쿼리를 작성하여 Board에서 Subscribe에서 찾는 해당 구독자의 게시글을 가져왔습니다.
