return [

    REPO_URL : 'https://github.com/strangecodee/sure-fire-plugin-app.git',

    BRANCH : 'dev',

    INVENTORY : 'ansible/inventory/prod.ini',

    PLAYBOOK : 'ansible/deploy.yml',

    ENVIRONMENT_NAME : 'prod',

    SLACK_CHANNEL_NAME : '#build-status',

    ACTION_MESSAGE : 'Application Successfully Deployed',

    KEEP_APPROVAL_STAGE : true,

    EXTRA_ARGS : ''
]