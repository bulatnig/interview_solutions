# Pollen Engineering Interview Project

## Introduction

Welcome to the Pollen Engineering Interview Project. This document will walk you through the technical interview, what to expect from it, and how to prepare for it. The agenda for the meeting will go as follows:

- Short introduction from everyone (5 minutes)
- Code Review (30 minutes)
- Implementation (30 minutes)
- break (optional)
- Problem solving scenario questions (30 minutes)
- Your questions

The Interview is going to last 2 hours. We will suggest a break after the first two sections.
We will leave some time at the end for your questions too.

We want the process to be as stress free as possible. There are a lot of details on how to prepare below.
Do let us know before the meeting if anything in this project is confusing or misleading. Also let us know if something doesn't work!

## Preparing for the interview

You already have the code downloaded and you are reading this file, which is great ⭐️
The Interview Project consists of two small projects: `ticketing` and `recommendations`.

Before the meeting we would like you to take a look at the `ticketing` project the way you would check your colleague's work. More details on what to look at exactly can be found below in the Code Review section. We will only ask you to review the code in `ticketing`. You will not have to make any changes to it.

If you have more time, you could also set up the virtual environment for the code (instructions below), check if it works and have a look at the `recommendations` project structure. You will be adding code to this project during the interview. This is completely optional and we will have time to set this up otherwise during the interview itself.

## What if I don't know Django?

No worries! The project is written in Django. That is what we use in most of our backend services. Our services are structured very differently compared to the standard Django project structure. It has proven to not scale well for our case (and we can talk about that during the interview if you are interested). Because of that _we do not require experience with Django_. As long as you understand Python code it's all good 😎
In order to get you up to speed with the interview project we added short descriptions of what each file is for in case you are not familiar with Django projects. You don't need to spend time reading Django docs just to prepare for our interview. We will not ask you anything specific to Django itself 🙂

## Project bootstrap

In this section you will set up the project to run on your local environment, along with some example data in
the database. You will be able to check if it works properly.

The code is prepared to run on python 3.6+. With python 3 installed you can run the following commands starting in the directory at the level of this file:

```
python3 -m venv venv
. ./venv/bin/activate
pip install -r ./requirements.txt
(cd ./ticketing && python manage.py migrate && python loaddata example_data)
(cd ./recommendations && python manage.py migrate && python loaddata example_data)
```

And if you are on Windows:

```
python3 -m venv venv
.\venv\Scripts\activate
pip install -r ./requirements.txt
(cd ./ticketing && python manage.py migrate && python loaddata example_data)
cd ..
(cd ./recommendations && python manage.py migrate && python loaddata example_data)
```

In order to check if the ticketing project works run the following:

```
cd ticketing
python manage.py runserver 3000
```

You can check in the browser if the following works http://127.0.0.1:3000/users/ with a simple JSON response page.

To check if the recommendations project works run the following:

```
cd recommendations
python manage.py runserver 4000
```

You can check in the browser if the following works http://127.0.0.1:4000/recommendations/1/ with a simple JSON response page.

## Code review

You will be reviewing your teammate's work.

You have a piece of code from a fellow developer and they'd want to know what can be improved
in their code as they'd like to get better at what they are doing.
At Pollen the center and core logic of our code is around selling tickets to experiences. Your colleague has just started a small project with the purpose to store tickets that can be bought, our users, and their orders.
This new project and their code is in the `ticketing` directory. We'd like you to spend a bit of time to understand the code the way you would normally do in order to give valuable feedback to someone.

We will spend up to 30 minutes during the interview to go through your feedback.

What we're looking for in this part:

- what you normally pay attention to when you're reviewing the code of your teammate
- how you communicate feedback to your colleagues
- what do you like about the code and what could be improved
- how do you explain your various points and ideas for improvement (why? how?)
- comments regarding the architecture of the existing solution, as well as performance, readability, and testing

You can come to the interview and just talk about the code, or you can make notes beforehand, add comments in the files, upload it to a private github repository and comment on it there. How you approach it is up to you and for your convenience only.

## Implementation

You will be writing additional code using your collegue's work.

Now that you have reviewed the part about tickets, users and orders and you know how it works. You will
need to add some functionality which will be using the `ticketing_api` data from its endpoints in order to
recommend rewards to users. Rewards can be anything from free swag, through VIP area access on concerts,
up to free ticket to these concerts. Each person earns points for their orders.
Their available points are stored on the `User` profile. We need a way to expose which rewards can be
awarded to a user up to the limit of points they currently have. The points will change over time and
we'd like to have the reward recommendations fresh, so we need a way to calculate what is available to the
user and make sure it's refreshed.

Your objective is to prepare a small version of a `Reward` recommendation mechanism. Your collegue prepared
a bit of stub code here and there and you need to build on it:

- a command to run to refresh the recommendations in:
  `recommendations/rewards/management/commands/refresh_recommendations.py`
- a basic `Recommendation` model in `models.py` along with a corresponding view in
  `recommendations/rewards/views.py`.
- a minimal `ticketing_api` client which will retrieve users and their orders in:
  `recommendations/rewards/ticketing_api_client.py`
- a model for `Reward` is prepared in `models.py` along with how many points it "costs"

Based on the above you should update the `refresh_recommendations.py` script in a way that it:

- recommends multiple rewards of the same type if possible, e.g. if a user has 200 points, a reward for 100 points can be recommended twice
- uses the `ticketing_api_client` to get orders made by users
- users get their rewards recommended based on how many points they've earned (stored in `ticketing_api` `User.points`)
- prepares `Reward` recommendations and stores them in the `Recommendation` model (you will need to update the `Recommendation` model to accomodate that)
- can be run multiple times producing the same results for the same users with their orders
- it has tests in the `tests` section for the most important part of the code

What we're looking for in this part:

- how you approach problem solving, what is your thinking process
- what decisions you make around writing code when you don't have much time
- what things you decide to test

## Problem solving scenario questions

In this section we will go through a few scenarios which are similar to what we deal with
on a day-to-day basis. This part is about just getting to better understand you and how you operate.
Our scenarios are not tricky or misleading, we don't want to surprise you. We designed our scenarios
based on real life problems we had to tackle in the tech team 🙂

What we're looking for in this part:

- how you approach problem solving
- what your thinking process is, both when you know exactly what to do and when you are not sure

## Your questions

At the end we'll leave some time for your questions. We won't judge you based on those questions.
We realise that interview processes work both ways. This is time for you to get to know Pollen better
from eyes of other engineers and decide if you're interested in joining us.
