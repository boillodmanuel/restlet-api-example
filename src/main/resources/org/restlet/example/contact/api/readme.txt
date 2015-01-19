Public resources:

- GET  /

Resources restricted to authenticated user with USER (user/password):

- GET  /companies
- POST /companies

- GET  /companies/{companyId}
- PUT  /companies/{companyId}

- GET  /contacts/
- POST /contacts/

- GET  /contacts/{contactId}
- PUT  /contacts/{contactId}

Resources restricted to authenticated user with role ADMIN (admin/password):

- DELETE /companies/{companyId}
- DELETE /contacts/{contactId}

Example:

curl http://localhost:8000/companies -u user:password
curl http://localhost:8000/companies -u user:password -H 'accept: application/xml'
curl http://localhost:8000/companies -u user:password -H 'accept: application/x-yaml'
