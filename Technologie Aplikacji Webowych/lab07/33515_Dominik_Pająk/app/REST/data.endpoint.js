const posts = [
    {
        id: 1,
        title: 'Post 1',
        text: 'Bacon ipsum dolor amet landjaeger shank ribeye ground round, ham hock hamburger prosciutto tail pork belly tri-tip pork loin ham corned beef beef ribs picanha. Strip steak pastrami bacon bresaola brisket.',
        image: 'https://www.stockvault.net/data/2019/08/31/269064/thumb16.jpg'
    },
    {
        id: 2,
        title: 'Post 2',
        text: ' Swine pork chop shankle, landjaeger leberkas tongue beef ribs pork belly fatback. ',
        image: 'https://cdn.pixabay.com/photo/2017/05/09/21/49/gecko-2299365_960_720.jpg'
    },
    {
        id: 3,
        title: 'Post 3',
        text: 'Kevin buffalo leberkas shank bresaola, meatloaf kielbasa frankfurter. Beef t-bone shank ham hock rump filet mignon picanha brisket ball tip short ribs capicola short loin buffalo chicken porchetta.',
        image: 'https://www.zuivelhoeve.nl/wp-content/uploads/2016/04/download-images-of-gentle-dogs-6866-1-1024x640.jpg'
    },
    {
        id: 4,
        title: 'Post 4',
        text: 'Prosciutto sausage alcatra hamburger leberkas turkey picanha salami strip steak porchetta kielbasa tail.',
        image: 'https://www.earthintransition.org/wp-content/uploads/2011/04/polar-bears-putin-1.jpg'
    },
    {
        id: 5,
        title: 'Post 5',
        text: 'Cupim beef ribs tenderloin, ribeye meatball shankle tongue fatback. Andouille shoulder salami hamburger, frankfurter sausage kielbasa t-bone turkey shankle chicken sirloin rump. ',
        image: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTLojrAHNeZsU_ykTz_GyOIhoa5eGQuTIxSAQ&usqp=CAU'
    },
    {
        id: 6,
        title: 'Post 6',
        text: 'Tail salami pancetta spare ribs chicken ball tip shankle landjaeger strip steak kevin short ribs beef meatball. Pancetta porchetta pork belly short ribs short loin. Jowl short loin turducken picanha ball tip kielbasa pig pork chop beef turkey venison.',
        image: 'https://picjumbo.com/wp-content/uploads/the-golden-gate-bridge-sunset-1080x720.jpg'
    },
    {
        id: 7,
        title: 'Post 7',
        text: 'Kielbasa landjaeger alcatra, pork hamburger tenderloin beef filet mignon t-bone ground round prosciutto picanha. ',
        image: 'http://getwallpapers.com/wallpaper/full/d/9/8/382442.jpg'
    },
    {
        id: 8,
        title: 'Post 8',
        text: 'Ham cupim pastrami salami alcatra tenderloin pork chop flank leberkas kevin bresaola doner tongue. Rump tenderloin sirloin, picanha pig burgdoggen ribeye boudin swine tri-tip strip steak pastrami.',
        image: 'https://www.stockvault.net/data/2007/03/01/102413/thumb16.jpg'
    },
    {
        id: 9,
        title: 'Post 9',
        text: 'Turducken short ribs boudin, strip steak tri-tip ball tip turkey shankle prosciutto tenderloin pork chop.',
        image: 'https://stokpic.com/wp-content/uploads/2017/07/Friends-Playing-In-The-Sea-and-Spashing-Water-2-400x284.jpg'
    },
    {
        id: 10,
        title: 'Post 10',
        text: 'Buffalo chicken boudin leberkas. Shoulder spare ribs tongue chicken drumstick tenderloin venison pancetta pork salami hamburger rump cupim alcatra meatloaf. Turkey chuck beef ribs porchetta, cupim beef pancetta kevin tri-tip tongue swine leberkas strip steak pastrami. ',
        image: 'https://www.pandasecurity.com/en/mediacenter/src/uploads/2013/11/pandasecurity-facebook-photo-privacy.jpg'
    },
];


const dataEndpoint = (router) => {
    router.get('/api/posts', async (request, response, next) => {
        response.status(200).send({ posts: posts });
    });

    router.post('/api/posts', async (request, response, next) => {
        posts.push(request.body.newPost);

        response.status(200).send({ post: posts[posts.length - 1] });
    });

    router.get('/api/posts', async (request, response, next) => {
        const post = posts.find((item => item.id === request.body.id));

        response.status(200).send({ post: post });
    });

    router.put('/api/posts', async (request, response, next) => {
        const post = posts.find((item => item.id === request.body.id));
        post.title = request.body.title;
        post.text = request.body.text;
        post.image = request.body.image;

        response.status(200).send({ post: post });
    });
};

export default dataEndpoint;