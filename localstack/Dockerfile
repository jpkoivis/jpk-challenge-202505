FROM localstack/localstack:3.2.0
COPY scripts/init.sh /etc/localstack/init/ready.d/start-localstack.sh
RUN chmod +x /etc/localstack/init/ready.d/start-localstack.sh
COPY data/ /data
