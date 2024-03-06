
## 기능 요구 사항
(🫸🏻 : 추가된 요구사항으로 우선순위를 뒤로 미룸.)

- 입출력
  - [] 게임에 참여할 사람의 이름을 입력한다.
    - [] 이름들은 쉼표(",")로 구분하여 입력해야한다.
    - [] 입력값은 쉼표로 시작하거나 끝날 수 없다.🫸🏻

  - [] 처음 받은 카드를 출력한다.
    - [] 딜러는 첫 번째로 받은 카드 한장만 출력한다.
    - [] 플레이어는 받은 모든 카드를 출력한다.

  - [] 플레이어가 카드를 더 뽑을지 입력한다.
    - [] 입력은 대소문자 구분없이 y("예") 또는 n("아니오")을 입력받는다.
    - [] y 또는 n 외의 값은 입력할 수 없다.🫸🏻

  - [] 플레이어가 카드를 뽑을 때마다 들고있는 카드들을 출력한다.
  
  - [] 딜러가 추가로 카드를 받을 때 마다 카드를 받았다고 출력한다.

  - [] 딜러와 플레이어의 전체 카드 목록과 결과를 출력한다.

  - [] 최종 승패를 출력한다.
    - [] 딜러에 대한 승패를 출력한다.
    - [] 플레이어에 대한 승패를 출력한다.

    <br>

- 카드 (Card)
  - [x] 문양을 가지고 있다.
    - [x] 문양은 ``하트, 스페이스, 클로버. 다이아몬드``가 있다.
  - [x] 번호를 가지고 있다.
    - [] ``2 ~ 10``범위의 수와 ``Q, J, K (10), A (1 또는 11)``의 수가 있다.

<br>

- 카드 덱 (Card Deck)
  - [x] 하나의 덱은 52장의 카드로 구성되어 있다.
  - [x] 맨 위의 카드를 뽑을 수 있다.
  - [x] 블랙잭 룰에 따라 6세트의 카드덱을 가진다.
    - [x] 카드의 순서는 섞여있다.
    - [x] 맨 위에서 부터 한 장씩 뽑는다. 
    - [x] 해당 덱의 카드를 모두 뽑았는지 확인한다.

<br>

- 블랙잭 게임 (BlackJackGame)
  - [] 처음에 딜러와 플레이어에게 카드를 2장씩 준다.
  - [] 플레이어에게 카드를 더 뽑을지 묻는다.
    - [] 카드를 더 뽑았을 경우 합계가 21을 초과한다면 "버스트" 임으로 더 이상 묻지 않는다.
    - [] 6개의 덱을 돌아가면서 카드를 뽑는다.
  - [] 딜러가 "버스트" 인지를 판단한다.

<br>

- 딜러 (Dealer)
  - [x] 합계가 17 이상이 될 때 까지 카드를 뽑는다.
  - [x] 카드의 점수를 알 수 있다.

<br>

- 플레이어 (Player)
  - [] 플레이어는 2명에서 8명까지만 참가 가능하다.🫸🏻
  - [] 플레이어 이름은 중복될 수 없다.🫸🏻
  - [x] 카드를 뽑는다.
  - [] 카드의 점수를 알 수 있다.

<br>

- 핸즈 (Hands)
  - [x] 카드를 뽑아서 저장한다.
  - [x] 카드 합계를 계산할 수 있다.
  - [x] 카드 합계가 21을 초과하는지 아닌지를 판단한다.

<br>

- 플레이어 이름(Name)
  - [] 이름의 길이는 1이상 5이하이어야 한다.🫸🏻
  - [] 공백을 이름으로 가질 수 없다.🫸🏻
  - [] 이름의 앞 뒤 공백은 제거 된다.🫸🏻

<br>

- 게임결과 (GameResult)
  - [] 최종 승패 결정한다. 
    - [] 플레이어가 21점을 넘지 않으면서 딜러보다 점수가 높은 경우 플레이어가 승리한다.
    - [] "버스트"
      - [] 딜러 버스트면 버스트가 아닌 모든 플레이어들이 승리한다.
      - [] 딜러가 버스트가 아니라면 카드의 합계가 21에 제일 가까운 사람이 승리한다.
      - [] 딜러를 포함한 모든 참가자들이 버스트한 경우에는 딜러가 승리한다.
      - [] 플레이어의 점수가 21점 이하이며 딜러가 버스트한 경우 플레이어가 승리한다.
    - [] "블랙잭"
      - [] 딜러가 블랙잭을 기록했고 플레이어는 못 한 경우 딜러가 승리한다.
      - [] 딜러를 포함한 모든 참가자들이 블랙잭이거나, 점수가 동일한 경우 무승부로 간주한다.
      - [] 딜러와 플레이어가 블랙잭이면, 무승부로 간주한다.
