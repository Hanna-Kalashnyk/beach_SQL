# SAGAX TEST TASK
This is a test task to check your **Java** and **SQL** skills.
Please do not upload results on github or similar. Just send them in archive.

# Welcome to Baywatch.
We have beaches and lifeguards on them.
Lifeguards swim at a specific speed and are assigned to a specific beach. The lifeguard may or may not have a boss.
Please help us to improve our system.

# Data init and result check
Test BaywatchTest must be passed correctly. Please do not modify it.
You can use src/test/resources/baywatch_init.sql to init test data for your convenience
## Task #1
Write **native SQL** queries according to tasks. See ***TODO*** in **Repository** classes
1) Get a list of the names of the beaches where the number of lifeguards does not exceed N people.
2) Get a list of beach names with the maximum overall speed of lifeguards. For example overall speed of Malibu is 14
3) Get a list of the names of the lifeguards who have faster speed than their bosses.
4) Get a list of the names of the fastest lifeguards.
5) Get a list of bosses. The boss is the one who has subordinates.
## Task #2
Make some modification in services. See ***TODO*** in **Service** classes
1) Implement deleting lifeguard by name using **Java Stream API**.
2) Implement updating lifeguard as you see fit.
3) Implement grouping and count lifeguards by speed using **Java Stream API**.

