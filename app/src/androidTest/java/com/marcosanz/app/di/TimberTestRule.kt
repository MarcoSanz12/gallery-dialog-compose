package com.marcosanz.app.di

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import timber.log.Timber

class TimberTestRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                Timber.plant(Timber.DebugTree())
                try {
                    base.evaluate()
                } finally {
                    Timber.uprootAll()
                }
            }
        }
    }
}