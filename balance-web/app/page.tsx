import { Button } from "@/components/ui/button";
import { BarChart3, LogIn, UserPlus } from "lucide-react";
import Link from "next/link";

export default function WelcomePage() {
  return (
    <main className="h-screen flex flex-col items-center justify-center gap-4">

      <BarChart3 size={120} />

      <header className="text-center">
        <h1 className="text-3xl font-semibold">Balances</h1>
        <div className="text-gray-600">Create your organization and manage your balance.</div>
      </header>

      <nav className="space-x-2">
        <Button render={<Link href={'/signup'} />} nativeButton={false}>
          <UserPlus /> Sign Up
        </Button>

        <Button variant={'outline'} render={<Link href={'/signin'} />} nativeButton={false}>
          <LogIn /> Sign In
        </Button>

      </nav>

    </main>
  )
}