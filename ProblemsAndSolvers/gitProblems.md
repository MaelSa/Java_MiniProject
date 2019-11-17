If we cannot do `git pull origin master` because
```bash
> From github.com:ogugugugugua/Java_MiniProject

> * branch            master     -> FETCH_HEAD

> fatal: refusing to merge unrelated histories
```
we should try `git pull origin master --allow-unrelated-histories`
