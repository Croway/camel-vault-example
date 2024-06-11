## How to execute the example

Run hashicorp vault

`docker run --cap-add=IPC_LOCK -e 'VAULT_DEV_ROOT_TOKEN_ID=myroot' -e 'VAULT_DEV_LISTEN_ADDRESS=0.0.0.0:8200' -p 8200:8200 hashicorp/vault:1.16.1`

Go to `http://localhost:8200/ui/vault/secrets/secret/kv/list` create a secret named `database` and a secret data `password` with value `mysecretpassword`

Run postgres

`docker run -p 5432:5432 --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword postgres`

Run the java application, once the application starts, create the users table via `http://localhost:8080/create` and query the table via `http://localhost:8080/users` - this is done via java configuration

in the console log, the yaml log can be observed - done via yaml configuration