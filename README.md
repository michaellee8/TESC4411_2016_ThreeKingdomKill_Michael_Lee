# Heros of Three Kingdoms
[API ref](http://www.comp.hkbu.edu.hk/~mandel/kingdom/)

This is the final project of course TECS4411 "Coding Heroes of the Three Kingdoms", studied at 2016 summer holiday.

The aim of such a project is to imoement skill learnt in the course.

Partner is/are: 

1. Alex Lee (@alexlee5354)

## This is Michael Lee's branch
Please read my code here, don't edit without my consent.
Stable and tested verision of code will be merged back to `master`.
Commit your code in your own branch.

##TO-DOs
1. Implement `AICommon.java`, which implements common action for every `Hero`.
2. Implement hero-specific AI, with name `AI<HeroName>.java`, e.g. `AIZhaoYun.java`.
3. Debug and edit.
4. Submission!

##Reminders
* Implement `beforeMyTurn()` and `afterMyTurn()` for every `Hero`.
* Override `canDefense()` and `canAttack()` method for `HeroZhaoYun.java`.
* Implement `join()` and `soec()` (if appliable) for each Hero

## Further Target
* It seems that we can change some behavior and attritbute of the game if we implement the `Hero` classes by ourself, let's test those tricks after finishing TO-DOs. Check out the decompiled source code file for how to do that.
