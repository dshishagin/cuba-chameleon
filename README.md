# CUBA and Chameleon integration

This is a sample application that shows how to make [Chameleon tours creating tool](https://www.trychameleon.com/) 
working fine with Java applications based on [CUBA Platform](https://www.cuba-platform.com/).

Although CUBA-based applications works just fine with Chameleon, there are couple questions that need to be addressed:

1. How to include custom [Chameleon JS-snippet](https://www.trychameleon.com/setup/go-live) that is used by Chameleon 
tool on production to **every** page of the CUBA app.
1. How to [pass custom fields to Chameleon](https://www.youtube.com/watch?v=2GZueVdBPGw&list=PLu0g_GBxlYcmVU1fRJ7HPmDzBKDJAUFep&index=5)
like user ID, user role and some others to help Chameleon to 
[target users](https://www.youtube.com/watch?v=poYJ2Rq57dI&index=4&list=PLu0g_GBxlYcmVU1fRJ7HPmDzBKDJAUFep).
1. How to make Chameleon 
[selecting the right element](https://www.youtube.com/watch?v=KI26PLlEsr4&list=PLu0g_GBxlYcmVU1fRJ7HPmDzBKDJAUFep&index=3) 
on the page if we have a lot of similar elements on the screen, for example if we need to select one of two "Save" buttons,  
or one specific table row out of many quite identical ones.

This app demonstrates solutions for this topics.
The demo is based on our [classical Bicycle Workshop example](https://github.com/cuba-platform/sample-workshop).

## Bicycle Workshop

Sample application from [Full-scale CUBA application: Step by Step Guide](https://github.com/cuba-platform/sample-workshop/wiki) tutorial.

The Workshop application is a simple order management system that enables tracking orders made by clients. Each order contains information about client, mechanic, spare parts used and services provided. Clients, mechanics, spare parts and orders can be created, edited and deleted through the system user interface.

This sample covers basic and most used features of the CUBA Platform, including development approach and runtime features of the platform.

Based on CUBA Platform 6.10.3

## Issues
Please use https://www.cuba-platform.com/discuss for discussion, support, and reporting problems coressponding to this sample.
