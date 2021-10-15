FROM python:3.9-slim
WORKDIR /app
COPY poetry.lock pyproject.toml ./
RUN pip install poetry
COPY . .
RUN poetry config virtualenvs.create false \
    && poetry install --no-dev --no-interaction --no-ansi
CMD ["bash", "./docker_entrypoint.sh"]