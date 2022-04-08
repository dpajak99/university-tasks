import userManager from './user.manager';
import postManager from './post.manager';


function getter(manager, request) {
    return function () {
        return manager.create(request, this);
    };
}

export default {
    getUserManager: getter(userManager),
    getPostManager: getter(postManager)
};
