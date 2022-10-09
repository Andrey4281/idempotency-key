В папке проекта manifests необходимо выполнить следующие инструкции:
(Установка postgres)
1) kubectl apply -f postgres-namespace.yaml
2) kubectl apply -f postgres-secret.yaml
3) helm -n postgres install otus -f my-postgresql-values.yaml ./postgresql

В папке проекта manifests необходимо выполнить следующие инструкции:
(Установка самого сервиса заказов)
1) kubectl apply -f backend-namespace.yaml
2) kubectl apply -f postgres-config-backend.yaml
3) kubectl apply -f postgres-secret-backend.yaml
4) kubectl apply -f idempotency-deployment.yaml

В папке проекта manifests необходимо выполнить следующие инструкции для установки ingress:
1) kubectl create namespace ingress-nginx
2) helm install gateway --namespace ingress-nginx \
      --values nginx-config.yaml \
      ingress-nginx/ingress-nginx
3) kubectl apply -f ingress.yaml