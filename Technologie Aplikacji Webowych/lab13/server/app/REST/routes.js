import userEndpoint from './user.endpoint';
import postEndpoint from './post.endpoint';

const routes = function (router) {
    userEndpoint(router);
    postEndpoint (router);
};

export default routes;
