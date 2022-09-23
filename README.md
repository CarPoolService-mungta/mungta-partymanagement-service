# PartyManagement

## 실행방법

```
mvn spring-boot:run
```

## 특이사항

```
```

## 필요기능 정의

|목차|화면|기능|수행작업|
|---|---|---|---|
|1|역할선택|운전자 선택 버튼 클릭|운전정보 등록 화면으로 이동|
|||카풀러 선택 버튼 클릭|카풀리스트 조회 화면으로 이동|
|2|운전정보등록	| |출발지 입력|출발지 맵 모달로 위치 검색 후 지오코딩해서 출발지 가져오기|
|	|	|도착지 입력	 | 도착지 맵 모달로 출발지와 동일한 기능 수행|
|	|	|출발시간 입력 | form에 들어가는 date (LocalDateTime)|
|	|	|이동거리 계산 | 출발지, 도착지 계산됨에 따라 이동거리 계산 수행|
|	|	|파티정원 입력 | form에 들어가는 최대 카풀 파티 정원|
|	|	|등록 버튼 클릭| axios 호출해서 db에 데이터 저장 후 진행 중인 카풀 화면으로 이동|
|	|	|취소 버튼 클릭| 역할 선택하기 화면으로 이동|
|3|	맵 모달|	위치 검색	| 위치 검색하면 해당 위치 기반으로 맵 이동|
|	|	|위치 클릭 | 해당 위치 정보 있음 |
|	|	|등록 버튼 클릭	| 해당 modal값 parent로 전송 |
|	|	|취소 버튼 클릭	| modal 닫고 삭제 |
|4|	파티리스트 |	카풀리스트 조회	| 참여가능한 파티리스트만 조회(OPEN, FULL) //FULL은 취소 시 가능이니|
|	|	|페이징	|페이지화 시켜서 페이지별로 이동 |
|	|	|서치조건 변경	|서치버튼 옆 필터 클릭 시 조건 설정하는 팝업 나오고 Parent의 condition값을 변경해준다 |
|5|	진행중인 카풀|	진행 중인 내용 조회| 본인이 포함된 카풀이고, OPEN, FULL, STARTED|
|||		리스트 클릭 (시간순)	|해당 정보 상세화면으로 이동(OPEN, FULL 일때)|
||||운전자/카풀러에 따라 정산화면(STATED,CLOSED 일때)|
|6|	지난 카풀내역|	지난 카풀 내역 조회	| 본인이 포함된 카풀이고, CLOSED만 가져온다|
| |		|서치조건 변경| 서치버튼 옆 필터 클릭 시 조건 설정하는 팝업 나오고 Parent의 condition값을 변경해준다|
|7|	정산화면|	정산 확인완료 버튼	| 운전자가 해당 카풀러의 정산요청 상태일때 확인 완료 버튼을 클릭하면, 운전자및 카풀러의 지불상태가 완료로 변경된다|
||| |전체 완료 시 카풀파티의 상태가 CLOSED로 변경된다|
|||	지불하기 버튼	| 운전자의 KakaoPay SettlementUrl로 접속한다|
|||	지불요청 버튼	| 해당 카풀러의 지불 상태를 요청으로 변경한다|
|||	리뷰하기 버튼	| 리뷰하기 화면으로 이동한다|
|||	신고하기 버튼	| 신고하기 화면으로 이동한다|







## Packaging and Running in docker environment

```
mvn package -B -DskipTests
docker build -t username/PartyManagement:v1 .
docker run username/PartyManagement:v1
```

## Push images and running in Kubernetes

```
docker login
# in case of docker hub, enter your username and password

docker push username/PartyManagement:v1
```

Edit the deployment.yaml under the /kubernetes directory:
```
    spec:
      containers:
        - name: PartyManagement
          image: username/PartyManagement:latest   # change this image name
          ports:
            - containerPort: 8080

```

Apply the yaml to the Kubernetes:
```
kubectl apply -f kubernetes/deployment.yaml
```

See the pod status:
```
kubectl get pods -l app=PartyManagement
```

If you have no problem, you can connect to the service by opening a proxy between your local and the kubernetes by using this command:
```
# new terminal
kubectl port-forward deploy/PartyManagement 8080:8080

# another terminal
http localhost:8080
```

If you have any problem on running the pod, you can find the reason by hitting this:
```
kubectl logs -l app=PartyManagement
```

Following problems may be occurred:

1. ImgPullBackOff:  Kubernetes failed to pull the image with the image name you've specified at the deployment.yaml. Please check your image name and ensure you have pushed the image properly.
1. CrashLoopBackOff: The spring application is not running properly. If you didn't provide the kafka installation on the kubernetes, the application may crash. Please install kafka firstly:

https://labs.msaez.io/#/courses/cna-full/full-course-cna/ops-utility

