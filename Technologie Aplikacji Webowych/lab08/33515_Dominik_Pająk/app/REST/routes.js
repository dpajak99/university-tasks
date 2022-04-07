import dataEndpoint from './data.endpoint';
import userEndpoint from './user.endpoint';

const routes = function (router)
{
    dataEndpoint(router);
    userEndpoint(router);
};

export default routes;