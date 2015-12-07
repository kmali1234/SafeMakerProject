// An example configuration file
exports.config = {
  // The address of a running selenium server.
  seleniumAddress: 'https://qaint-54.sysnet.ie/safemaker/personalisation',

  // Capabilities to be passed to the webdriver instance.
  capabilities: {
    'browserName': 'firefox'
  },

  // Spec patterns are relative to the configuration file location passed
  // to protractor (in this example conf.js).
  // They may include glob patterns.
  specs: ['Validation.js'],

  // Options to be passed to Jasmine-node.
  jasmineNodeOpts: {
    showColors: true, // Use colors in the command line report.
  }
}; 
