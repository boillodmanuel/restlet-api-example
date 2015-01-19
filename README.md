
curl http://localhost:8000/companies
curl http://localhost:8000/companies -H 'accept: application/xml'
curl http://localhost:8000/companies -H 'accept: application/x-yaml'

curl http://localhost:8000/companies/1
curl http://localhost:8000/companies/1 -H 'accept: application/xml'
curl http://localhost:8000/companies/1 -H 'accept: application/x-yaml'

curl http://localhost:8000/contacts
curl http://localhost:8000/contacts -H 'accept: application/xml'
curl http://localhost:8000/contacts -H 'accept: application/x-yaml'

curl http://localhost:8000/contacts/1
curl http://localhost:8000/contacts/1 -H 'accept: application/xml'
curl http://localhost:8000/contacts/1 -H 'accept: application/x-yaml'

curl http://localhost:8000/api-docs
curl http://localhost:8000/api-docs/companies
curl http://localhost:8000/api-docs/contacts

curl http://localhost:8000/swagger.json
