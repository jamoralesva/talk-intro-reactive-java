import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    stages: [
      { duration: '15s', target: 20 },
      { duration: '45s', target: 10 },
      { duration: '10s', target: 0 },
    ],
  };
  export default function () {
    let res = http.get('http://localhost:8080/api?age=5');
    check(res, { 'status was 200': (r) => r.status == 200 });
    //sleep(1);
  }