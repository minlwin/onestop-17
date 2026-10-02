import { Button } from "@/components/ui/button";
import { BarChart3, Home } from "lucide-react";
import Link from "next/link";

export default function AuthLayout({ children } : { children : Readonly<React.ReactNode>}) {
    return (
        <div className="h-screen flex">

            <header className="flex-1 bg-primary flex flex-col items-center justify-center gap-4">
                <BarChart3 color="white" size={120} />

                <div className="text-center">
                    <h1 className="text-white text-3xl font-semibold">Balances</h1>
                    <p className="text-gray-300">Welcome to Balance Management System</p>
                </div>

                <Button variant={'outline'} render={<Link href={'/'} />} nativeButton={false}>
                    <Home /> Home
                </Button>
            </header>

            <main className="flex-1 flex items-center justify-center">
                <section className="w-2/3">
                    {children}
                </section>
            </main>
        </div>
    )
}