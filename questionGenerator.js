const lorem = [
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
    'Fusce volutpat convallis egestas',
    'Quisque maximus neque vitae odio auctor congue',
    'Nullam nulla nisi, finibus in mauris vitae, vehicula consequat justo',
    'Cras eu sodales ipsum',
    'Sed consectetur dui vitae eros congue, sit amet tincidunt turpis finibus',
    'Nam congue nec dolor molestie auctor',
    'Vestibulum turpis mi, finibus vel mauris in, ullamcorper porta leo',
    'Aenean ultrices nisl accumsan neque fringilla vulputate',
    'Vivamus ornare leo ac venenatis rutrum',
    'Proin auctor tortor eget fermentum fermentum',
    'Sed vitae nulla et libero faucibus scelerisque',
    'Nunc elit elit, vulputate sed luctus ut, dignissim nec diam',
    'Suspendisse aliquam leo metus, ac euismod elit pretium quis',
    'Integer vulputate leo id massa semper luctus',
];

const questions = lorem.map(text => {
    const answersIndexes = [1, 2, 3, 4].sort(() => 0.5 - Math.random());
    return {
        text,
        answers: answersIndexes.map((e, i) => {
            const isCorrect = e === 1;
            return {
                text: `Question #${i}${isCorrect ? ' (Correct)' : ''}`,
                isCorrect,
            };
        }),
    };
});
